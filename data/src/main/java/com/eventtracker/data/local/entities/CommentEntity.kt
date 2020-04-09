package com.eventtracker.data.local.entities

import java.util.*
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CommentEntity (
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var userId: String = "",
    var userName: String = "",
    var text: String = "",
    var createdAt: Date = Date(),
    var replies: RealmList<ReplyEntity> = RealmList(),
    var attachments: RealmList<AttachmentEntity> = RealmList()
) : RealmObject()