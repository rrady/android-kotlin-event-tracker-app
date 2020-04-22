package com.eventtracker.data.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

import com.eventtracker.domain.models.Host

@IgnoreExtraProperties
data class FirebaseHost(
    @DocumentId
    val id: String,
    val ownerId: String,
    val name: String,
    val description: String,
    val info: String,
    val site: String,
    val phone: String,
    val email: String,
    val avatarUri: String
) {
    constructor(): this("", "", "", "", "", "", "", "", "")

    @Exclude
    fun toHost(): Host {
        return Host(id, ownerId, name, description, info, site, phone, email, avatarUri)
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "ownerId" to ownerId,
            "description" to description,
            "info" to info,
            "site" to site,
            "phone" to phone,
            "email" to email,
            "avatarUri" to avatarUri
        )
    }

    companion object {
        fun fromHost(host: Host): FirebaseHost {
            return FirebaseHost(host.id, host.ownerId, host.name, host.description, host.info, host.site, host.phone, host.email, host.avatarUri)
        }
    }
}