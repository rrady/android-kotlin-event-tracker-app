package com.eventtracker.data.cloud.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.Timestamp

import com.eventtracker.domain.models.Reply

data class FirebaseReply (
    @DocumentId
    val id: String,
    val commentId: String,
    val text: String,
    val createdAt: Timestamp
) {
    @Exclude
    fun toReply(): Reply {
        return Reply(id, commentId, text, createdAt.toDate())
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "commentId" to commentId,
            "text" to text,
            "createdAt" to createdAt
        )
    }

    companion object {
        fun fromReply(reply: Reply): FirebaseReply {
            return FirebaseReply(reply.id, reply.commentId, reply.text, Timestamp(reply.createdAt))
        }
    }
}