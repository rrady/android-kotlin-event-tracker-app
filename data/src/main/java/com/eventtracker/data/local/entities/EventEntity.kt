package com.eventtracker.data.local.entities

import java.util.*
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class EventEntity (
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var description: String = "",
    var createdAt: Date = Date(),
    var beginDate: Date = Date(),
    var endDate: Date = Date(),
    var photoUri: String = "",
    var commets: RealmList<CommentEntity> = RealmList()
) : RealmObject()