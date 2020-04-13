package com.eventtracker.domain.stores

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Event

interface EventStore {
    fun getEvent(id: String): ResultWrapper<Event?, Exception>
    fun saveEvent(event: Event): ResultWrapper<Unit, Exception>
}