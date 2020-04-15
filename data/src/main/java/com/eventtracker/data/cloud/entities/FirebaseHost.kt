package com.eventtracker.data.cloud.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

import com.eventtracker.domain.models.Host

@IgnoreExtraProperties
data class FirebaseHost(
    @DocumentId
    var id: String,
    val name: String,
    val description: String,
    val info: String,
    val site: String,
    val phone: String,
    val email: String,
    val address: String,
    val avatarUri: String
) {
    @Exclude
    fun toHost(): Host {
        return Host(id, name, description, info, site, phone, email, address ,avatarUri)
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "description" to description,
            "info" to info,
            "site" to site,
            "phone" to phone,
            "email" to email,
            "address" to address,
            "avatarUri" to avatarUri
        )
    }

    companion object {
        fun fromHost(host: Host): FirebaseHost {
            return FirebaseHost(host.id, host.name, host.description, host.info, host.site, host.phone, host.email, host.address, host.avatarUri)
        }
    }
}