package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Location

interface LocationRepository {
    suspend fun getLocation(id: String): ResultWrapper<Location?, Exception>
    suspend fun addLocation(location: Location): ResultWrapper<Unit, Exception>
    suspend fun updateLocation(location: Location): ResultWrapper<Unit, Exception>
    suspend fun deleteLocation(id: String): ResultWrapper<Unit, Exception>
}