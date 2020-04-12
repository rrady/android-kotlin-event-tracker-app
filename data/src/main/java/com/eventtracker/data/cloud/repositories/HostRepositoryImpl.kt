package com.eventtracker.data.cloud.repositories

import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.QuerySnapshot

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.repositories.HostRepository

import com.eventtracker.data.cloud.entities.FirebaseHost
import com.eventtracker.data.cloud.utils.awaitTaskCompletable
import com.eventtracker.data.cloud.utils.awaitTaskResult

const val HOST_COLLECTION = "hosts"

class HostRepositoryImpl: HostRepository {
    private val db = Firebase.firestore

    override suspend fun getHosts(): ResultWrapper<List<Host>, Exception> {
        var collection = db.collection(HOST_COLLECTION)

        return try {
            val task = awaitTaskResult(collection.get())

            return resultToHostList(task)
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun getHost(id: String): ResultWrapper<Host?, Exception> {
        var document = db.collection(HOST_COLLECTION).document(id)

        return try {
            val task = awaitTaskResult(document.get())

            ResultWrapper.build {
                task.toObject<FirebaseHost>()?.toHost()
            }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun addHost(host: Host): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(HOST_COLLECTION)
                .document(host.id)
                .set(FirebaseHost.fromHost(host))
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun updateHost(host: Host): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(HOST_COLLECTION)
                .document(host.id)
                .update(FirebaseHost.fromHost(host).toMap())
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun deleteHost(id: String): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(db.collection(HOST_COLLECTION)
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

        return ResultWrapper.build {
            hosts
        }
    }
}