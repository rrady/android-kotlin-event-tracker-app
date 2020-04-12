package com.eventtracker.data.cloud.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.Timestamp

import com.eventtracker.domain.models.Event

@IgnoreExtraProperties
data class FirebaseEvent(
    @DocumentId
    val id: String,
    val hostId: String,
    val locationId: String,
    val name: String,
    val description: String,
    val createdAt: Timestamp,
    val beginDate: Timestamp,
    val endDate: Timestamp,
    val photoUri: String
) {
    @Exclude
    fun toEvent(): Event {
        return Event(id, hostId, locationId, name, description, createdAt.toDate(), beginDate.toDate(),
            endDate.toDate(), photoUri)
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "hostId" to hostId,
            "address" to locationId,
            "name" to name,
            "description" to description,
            "createdAt" to createdAt,
            "beginDate" to beginDate,
            "endDate" to endDate,
            "photoUri" to photoUri
        )
    }

    companion object {
        fun fromEvent(event: Event): FirebaseEvent {
            return FirebaseEvent(event.id, event.hostId, event.locationId, event.name, event.description,
                Timestamp(event.createdAt), Timestamp(event.beginDate), Timestamp(event.endDate), event.photoUri)
        }
    }
}