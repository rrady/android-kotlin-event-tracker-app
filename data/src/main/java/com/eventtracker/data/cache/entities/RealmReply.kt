package com.eventtracker.data.cache.entities

import java.util.*
import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

open class RealmReply (
    @PrimaryKey
    var id: String = "",
    var commentId: String = "",
    var text: String = "",
    var createdAt: Date = Date()
): RealmObject()