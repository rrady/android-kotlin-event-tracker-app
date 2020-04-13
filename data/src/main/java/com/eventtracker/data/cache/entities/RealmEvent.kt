package com.eventtracker.data.cache.entities

import com.eventtracker.domain.models.Event
import java.util.*
import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

open class RealmEvent (
    @PrimaryKey
    var id: String = "",
    var hostId: String = "",
    var locationId: String = "",
    var name: String = "",
    var description: String = "",
    var createdAt: Date = Date(),
    var beginDate: Date = Date(),
    var endDate: Date = Date(),
    var photoUri: String = ""
): RealmObject() {
    fun toEvent(): Event {
        return Event(id, hostId, locationId, name, description, createdAt, beginDate, endDate, photoUri)
    }

    companion object {
        fun fromEvent(event: Event): RealmEvent{
            return RealmEvent(event.id, event.hostId, event.locationId, event.name, event.description, event.createdAt, event.beginDate, event.endDate, event.photoUri)
        }
    }
}