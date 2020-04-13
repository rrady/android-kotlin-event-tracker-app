package com.eventtracker.data.cache.entities

import com.eventtracker.domain.models.Comment
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
): RealmObject() {
    fun toComment(): Comment {
        return Comment(id, eventId, attachmentId, text, createdAt)
    }

    companion object{
        fun fromComment(comment: Comment): RealmComment {
            return RealmComment(comment.id, comment.eventId, comment.attachmentId, comment.text, comment.createdAt)
        }
    }
}