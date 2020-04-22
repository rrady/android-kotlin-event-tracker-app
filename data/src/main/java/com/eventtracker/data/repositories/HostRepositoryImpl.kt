package com.eventtracker.data.repositories

import android.net.Uri
import java.io.File
import java.util.*
import javax.inject.Inject
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.QuerySnapshot

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.repositories.HostRepository

import com.eventtracker.data.entities.FirebaseHost
import com.eventtracker.data.utils.awaitTaskCompletable
import com.eventtracker.data.utils.awaitTaskResult
import com.eventtracker.domain.exceptions.HostRepositoryException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

const val HOST_COLLECTION = "hosts"

class HostRepositoryImpl @Inject constructor(private val db: FirebaseFirestore,
    private val storage: FirebaseStorage): HostRepository {

    override suspend fun getHosts(): ResultWrapper<List<Host>, Exception> {
        var collection = db.collection(HOST_COLLECTION)

        return try {
            val task = awaitTaskResult(collection.get())

            return resultToHostList(task)
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun addHost(host: Host): ResultWrapper<Unit, Exception> {
        val storageUri = uploadImageToFirebaseStorage(host.avatarUri)
        host.avatarUri = storageUri

        return try {
            awaitTaskCompletable(
                db.collection(HOST_COLLECTION)
                    .add(FirebaseHost.fromHost(host))
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun deleteHost(id: String): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(
                db.collection(HOST_COLLECTION)
                    .document(id)
                    .delete()
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    private fun resultToHostList(result: QuerySnapshot?): ResultWrapper<List<Host>, Exception> {
        val hosts = mutableListOf<Host>()

        result?.forEach { document ->
            hosts.add(document.toObject<FirebaseHost>().toHost())
        }

        return ResultWrapper.build { hosts }
    }

    private suspend fun uploadImageToFirebaseStorage(avatarUri: String): String {
        val filename = UUID.randomUUID().toString()
        val reference = storage.getReference("$HOST_COLLECTION/$filename")
        val result = awaitTaskResult(reference.putFile(Uri.fromFile(File(avatarUri))))

        if (result.error != null) {
            throw Exception(result.error?.message?: "Failed to upload avatar") as HostRepositoryException
        }

        return awaitTaskResult(result.storage.downloadUrl).toString()
    }
}