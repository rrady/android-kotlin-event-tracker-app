package com.eventtracker.data.local.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

import com.eventtracker.domain.enums.AttachmentType
import java.util.*

open class AttachmentEntity (
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var type: String = "",
    var contentUri: String = ""
) : RealmObject()
