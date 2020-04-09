package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event
import java.util.*

interface EventRepository {
    fun getEvent(): ResultWrapper<List<Event>, Exception>
    fun getHostEventsByDate(hostId: String, createdAt: Date): ResultWrapper<List<Event>, Exception>
}