package com.eventtracker.data.cache.entities

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
): RealmObject()