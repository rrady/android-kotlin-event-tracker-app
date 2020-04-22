package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event

interface EventRepository {
    suspend fun getEventsForHost(hostId: String): ResultWrapper<List<Event>, Exception>
    suspend fun addEvent(event: Event): ResultWrapper<Unit, Exception>
    suspend fun deleteEvent(id: String): ResultWrapper<Unit, Exception>
}