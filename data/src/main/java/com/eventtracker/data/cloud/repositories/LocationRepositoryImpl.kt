package com.eventtracker.data.cloud.repositories

import com.google.firebase.ktx.Firebase

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Location
import com.eventtracker.domain.repositories.LocationRepository
import com.eventtracker.domain.exceptions.LocationRepositoryException

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database

const val LOCATION_COLLECTION = "locations"

class LocationRepositoryImpl: LocationRepository {
    private val db = Firebase.database.reference

    override suspend fun getLocation(id: String): ResultWrapper<Location?, Exception> {
        var document = db.child(LOCATION_COLLECTION).child(id)
        var result: Location? = null

            document.addValueEventListener(object: ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                      result = dataSnapshot.getValue(Location::class.java)
                }
            })
        return ResultWrapper.build { result?: throw LocationRepositoryException }
        }

    override suspend fun addLocation(location: Location): ResultWrapper<Unit, Exception> {
        var key: String? = db.child(LOCATION_COLLECTION).push().key
        val childUpdates = HashMap<String, Any>()
        childUpdates["/location/$key"] = location
        val result = db.updateChildren(childUpdates)

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw LocationRepositoryException }
        }
    }

    override suspend fun updateLocation(location: Location): ResultWrapper<Unit, Exception> {
        var result = db.child(LOCATION_COLLECTION).child(location.id).setValue(location)

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw LocationRepositoryException }
        }
    }

    override suspend fun deleteLocation(id: String): ResultWrapper<Unit, Exception> {
        var result = db.child(LOCATION_COLLECTION).child(id).removeValue()

        return if ( result.isSuccessful ) {
            ResultWrapper.build {Unit}
        } else {
            ResultWrapper.build { throw LocationRepositoryException }
        }
    }
}