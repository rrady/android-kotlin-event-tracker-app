package com.eventtracker.data.cloud.repositories

import android.net.Uri
import com.google.firebase.ktx.Firebase

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.repositories.HostRepository
import com.eventtracker.domain.exceptions.HostRepositoryException

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.storage.ktx.storage

const val HOST_COLLECTION = "hosts"

class HostRepositoryImpl: HostRepository {
    private val db = Firebase.database.reference
    private val storage = Firebase.storage.reference

    override fun getHosts(): ResultWrapper<List<Host>, Exception> {
        var document = db.child(HOST_COLLECTION)
        var results: MutableList<Host> = mutableListOf()

        document.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (hostSnapshot in dataSnapshot.children) {
                    val host: Host? = hostSnapshot.getValue(Host::class.java)
                    if (host != null) {
                        results.add(host)
                    }
                }
            }
        })
        return ResultWrapper.build { results?: throw HostRepositoryException }
    }


    override fun getHost(id: String): ResultWrapper<Host?, Exception> {
        var document = db.child(HOST_COLLECTION).child(id)
        var result: Host? = null

        document.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                result = dataSnapshot.getValue(Host::class.java)
            }
        })
        return ResultWrapper.build { result?: throw HostRepositoryException }
    }

    override fun addHost(host: Host): ResultWrapper<Unit, Exception> {
        var key: String? = db.child(HOST_COLLECTION).push().key

        val childUpdates = HashMap<String, Any>()
        childUpdates["/hosts/$key"] = host
        val result = db.updateChildren(childUpdates)

        storage.putFile(Uri.parse(host.avatarUri))

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw HostRepositoryException }
        }
    }

    override fun updateHost(host: Host): ResultWrapper<Unit, Exception> {
        var result = db.child(HOST_COLLECTION).child(host.id).setValue(host)

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw HostRepositoryException }
        }
    }

    override fun deleteHost(id: String): ResultWrapper<Unit, Exception> {
        var result = db.child(HOST_COLLECTION).child(id).removeValue()

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw HostRepositoryException }
        }
    }
}