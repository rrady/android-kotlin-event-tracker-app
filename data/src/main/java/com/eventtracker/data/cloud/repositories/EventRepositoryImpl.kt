package com.eventtracker.data.cloud.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event
import com.eventtracker.domain.repositories.EventRepository

const val EVENT_COLLECTION = "events"

class EventRepositoryImpl : EventRepository {
    override suspend fun getEventsForHost(hostId: String): ResultWrapper<List<Event>, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun addEvent(event: Event): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEvent(event: Event): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(id: String): ResultWrapper<Unit, Exception> {
        TODO("Not yet implemented")
    }
}