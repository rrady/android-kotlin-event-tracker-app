package com.eventtracker.app.ui.hostdetail

import java.util.*
import androidx.lifecycle.ViewModel

import com.eventtracker.domain.models.Event

class EventListViewModel: ViewModel() {
    private val hostId = UUID.randomUUID().toString()
    private val events = listOf<Event>(
        Event(UUID.randomUUID().toString(), hostId, UUID.randomUUID().toString(), "Event A", "Event A description", Date(), Date(), Date(), "https://i.imgur.com/7n4qdCX.jpg"),
        Event(UUID.randomUUID().toString(), hostId, UUID.randomUUID().toString(), "Event B", "Event B description", Date(), Date(), Date(), "https://i.imgur.com/7n4qdCX.jpg"),
        Event(UUID.randomUUID().toString(), hostId, UUID.randomUUID().toString(), "Event C", "Event C description", Date(), Date(), Date(), "https://i.imgur.com/7n4qdCX.jpg")
    )

    fun getEvents(): List<Event> {
        return events
    }
}