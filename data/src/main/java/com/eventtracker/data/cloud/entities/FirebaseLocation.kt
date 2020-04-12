package com.eventtracker.data.cloud.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

import com.eventtracker.domain.models.Location

@IgnoreExtraProperties
data class FirebaseLocation(
    @DocumentId
    val id: String,
    val country: String,
    val city: String,
    val street: String,
    val number: String,
    val zipCode: String,
    val latitude: Double,
    val longitude: Double
) {
    @Exclude
    fun toLocation(): Location {
        return Location(id, country, city, street, number, zipCode, latitude, longitude)
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "country" to country,
            "city" to city,
            "street" to street,
            "number" to number,
            "zipcode" to zipCode,
            "latitude" to latitude,
            "longitude" to longitude
        )
    }

    companion object {
        fun fromLocation(location: Location): FirebaseLocation {
            return FirebaseLocation(location.id, location.country, location.city,
                location.street, location.number, location.zipCode, location.latitude,
                location.longitude)
        }
    }
}