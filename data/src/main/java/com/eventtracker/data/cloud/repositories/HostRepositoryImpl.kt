package com.eventtracker.data.cloud.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.exceptions.HostRepositoryException
import com.eventtracker.domain.models.Host
import com.eventtracker.domain.repositories.HostRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.toObject
import java.lang.Exception

class HostRepositoryImpl: HostRepository {
    private val db = Firebase.firestore

    override fun getHosts(): ResultWrapper<List<Host>, Exception> {
        Log.d(TAG, "masuk load datas")
        var listMember: MutableList<Host>? = null
        db.collection("hosts")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    listMember = mutableListOf()
                    for (document in task.result!!) {
//                            Log.d(TAG, "dapet" + document.id + " => " + document.data)
                        listMember!!.add(document.toObject<Host>())
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
        return ResultWrapper.build {listMember?: throw HostRepositoryException}
    }

    override fun getHostsFollowedByUser(userId: String): ResultWrapper<List<Host>, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}