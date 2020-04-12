package com.eventtracker.data.cache.entities

import com.eventtracker.domain.models.Reply
import java.util.*
import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

open class RealmReply (
    @PrimaryKey
    var id: String = "",
    var commentId: String = "",
    var text: String = "",
    var createdAt: Date = Date()
): RealmObject() {
    fun toReply(): Reply {
        return Reply(id, commentId, text, createdAt)
    }

    companion object {
        fun fromReply(reply: Reply): RealmReply {
            return RealmReply(reply.id, reply.commentId, reply.text, reply.createdAt)
        }
    }
}