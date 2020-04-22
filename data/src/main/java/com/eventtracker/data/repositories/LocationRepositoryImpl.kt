package com.eventtracker.data.repositories

import javax.inject.Inject
import com.google.firebase.firestore.ktx.toObject

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Location
import com.eventtracker.domain.repositories.LocationRepository

import com.eventtracker.data.entities.FirebaseLocation
import com.eventtracker.data.utils.awaitTaskCompletable
import com.eventtracker.data.utils.awaitTaskResult
import com.google.firebase.firestore.FirebaseFirestore

const val LOCATION_COLLECTION = "locations"

class LocationRepositoryImpl @Inject constructor(private val db: FirebaseFirestore): LocationRepository {
    override suspend fun getLocation(id: String): ResultWrapper<Location?, Exception> {
        var document = db.collection(LOCATION_COLLECTION).document(id)

        return try {
            val task = awaitTaskResult(document.get())

            ResultWrapper.build {
                task.toObject<FirebaseLocation>()?.toLocation()
            }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun addLocation(location: Location): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(
                db.collection(LOCATION_COLLECTION)
                    .document(location.id)
                    .set(FirebaseLocation.fromLocation(location))
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }

    override suspend fun deleteLocation(id: String): ResultWrapper<Unit, Exception> {
        return try {
            awaitTaskCompletable(
                db.collection(LOCATION_COLLECTION)
                    .document(id)
                    .delete()
            )

            ResultWrapper.build { Unit }
        } catch (exception: Exception) {
            ResultWrapper.build { throw exception }
        }
    }
}