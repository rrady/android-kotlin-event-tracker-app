package com.eventtracker.data.cache.entities

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
): RealmObject()