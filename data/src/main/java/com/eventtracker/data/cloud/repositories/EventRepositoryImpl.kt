package com.eventtracker.data.cloud.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event
import com.eventtracker.domain.repositories.EventRepository
import java.util.*

class EventRepositoryImpl: EventRepository {
    override fun getEvent(): ResultWrapper<List<Event>, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHostEventsByDate(
        hostId: String,
        createdAt: Date
    ): ResultWrapper<List<Event>, Exception> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}