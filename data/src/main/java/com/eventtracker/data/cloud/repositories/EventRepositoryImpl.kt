package com.eventtracker.data.cloud.repositories

import com.google.firebase.ktx.Firebase

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event
import com.eventtracker.domain.repositories.EventRepository
import com.eventtracker.domain.exceptions.EventRepositoryException
import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database

const val EVENT_COLLECTION = "events"

class EventRepositoryImpl : EventRepository {
    private val db = Firebase.database.reference

    override suspend fun getEventsForHost(hostId: String): ResultWrapper<List<Event>, Exception> {
        var document = db.child(EVENT_COLLECTION).child(hostId)
        var results: MutableList<Event> = mutableListOf()

        document.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (hostSnapshot in dataSnapshot.children) {
                    val host: Event? = hostSnapshot.getValue(Event::class.java)
                    if (host != null) {
                        results.add(host)
                    }
                }
            }
        })
        return ResultWrapper.build { results?: throw EventRepositoryException }
    }

    override suspend fun addEvent(event: Event): ResultWrapper<Unit, Exception> {
        var key: String? = db.child(EVENT_COLLECTION).push().key
        val childUpdates = HashMap<String, Any>()
        childUpdates["/events/$key"] = event
        val result = db.updateChildren(childUpdates)

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw EventRepositoryException }
        }
    }

    override suspend fun updateEvent(event: Event): ResultWrapper<Unit, Exception> {
        var result = db.child(HOST_COLLECTION).child(event.id).setValue(event)

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw EventRepositoryException }
        }
    }

    override suspend fun deleteEvent(id: String): ResultWrapper<Unit, Exception> {
        var result = db.child(EVENT_COLLECTION).child(id).removeValue()

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw EventRepositoryException }
        }
    }
}