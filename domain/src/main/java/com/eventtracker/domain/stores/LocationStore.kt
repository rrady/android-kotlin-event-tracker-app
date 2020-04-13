package com.eventtracker.domain.stores

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Location
import java.lang.Exception

interface LocationStore {
    fun getLocation(id: String): ResultWrapper<Location?, Exception>
    fun saveLocation(location: Location): ResultWrapper<Unit, Exception>
}