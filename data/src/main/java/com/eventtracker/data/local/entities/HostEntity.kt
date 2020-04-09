package com.eventtracker.data.local.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class HostEntity (
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var description: String = "",
    var avatarUri: String = "",
    var events: RealmList<EventEntity> = RealmList()
) : RealmObject()
