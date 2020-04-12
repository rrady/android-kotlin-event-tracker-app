package com.eventtracker.data.cache.entities

import com.eventtracker.domain.models.Location
import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

open class RealmLocation (
    @PrimaryKey
    var id: String = "",
    var country: String = "",
    var city: String = "",
    var street: String = "",
    var number: String = "",
    var zipCode: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
): RealmObject() {
    fun toLocation(): Location {
        return Location(id, country, city, street, number, zipCode, latitude, longitude)
    }

    companion object {
        fun fromLocation(location: Location): RealmLocation {
            return RealmLocation(location.id, location.country, location.city, location.street, location.number, location.zipCode, location.latitude, location.longitude)
        }
    }
}