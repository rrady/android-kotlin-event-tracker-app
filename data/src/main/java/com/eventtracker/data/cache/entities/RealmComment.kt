package com.eventtracker.data.cache.entities

import java.util.*
import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

open class RealmComment (
    @PrimaryKey
    var id: String = "",
    var eventId: String = "",
    var attachmentId: String = "",
    var text: String = "",
    var createdAt: Date = Date()
): RealmObject()