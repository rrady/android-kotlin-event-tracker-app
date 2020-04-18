package com.eventtracker.data.cloud.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Location
import com.eventtracker.domain.repositories.LocationRepository

const val LOCATION_COLLECTION = "locations"

class LocationRepositoryImpl: LocationRepository {
    override suspend fun getLocation(id: String): ResultWrapper<Location?, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun addLocation(location: Location): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocation(location: Location): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLocation(id: String): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }
}