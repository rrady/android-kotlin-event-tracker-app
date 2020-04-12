package com.eventtracker.data.cloud.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

import com.eventtracker.domain.models.Host

@IgnoreExtraProperties
data class FirebaseHost(
    @DocumentId
    val id: String,
    val name: String,
    val description: String,
    val avatarUri: String
) {
    @Exclude
    fun toHost(): Host {
        return Host(id, name, description, avatarUri)
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "description" to description,
            "avatarUri" to avatarUri
        )
    }

    companion object {
        fun fromHost(host: Host): FirebaseHost {
            return FirebaseHost(host.id, host.name, host.description, host.avatarUri)
        }
    }
}