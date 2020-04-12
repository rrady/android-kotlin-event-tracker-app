package com.eventtracker.data.cache.entities

import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

open class RealmHost (
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var avatarUri: String = ""
): RealmObject()