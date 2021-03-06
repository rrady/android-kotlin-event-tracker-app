package com.eventtracker.data.repositories

import javax.inject.Inject
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.QuerySnapshot

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event
import com.eventtracker.domain.repositories.EventRepository

import com.eventtracker.data.entities.FirebaseEvent
import com.eventtracker.data.utils.awaitTaskCompletable
import com.eventtracker.data.utils.awaitTaskResult
import com.google.firebase.firestore.FirebaseFirestore

const val EVENT_COLLECTION = "events"

class EventRepositoryImpl @Inject constructor(private val db: FirebaseFirestore): EventRepository {
    override suspend fun getEventsForHost(hostId: String): ResultWrapper<List<Event>, Exception> {
        var collection = db.collection(EVENT_COLLECTION).whereEqualTo("hostId", hostId)

        return try {
            val task = awaitTaskResult(collection.get())

            return resultToEventList(task)
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun addEvent(event: Event): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(
                db.collection(EVENT_COLLECTION)
                    .document(event.id)
                    .set(FirebaseEvent.fromEvent(event))
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun deleteEvent(id: String): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(
                db.collection(EVENT_COLLECTION)
                    .document(id)
                    .delete()
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    private fun resultToEventList(result: QuerySnapshot?): ResultWrapper<List<Event>, Exception> {
        val events = mutableListOf<Event>()

        result?.forEach { document ->
            events.add(document.toObject<FirebaseEvent>().toEvent())
        }

        return ResultWrapper.build {
            events
        }
    }
}