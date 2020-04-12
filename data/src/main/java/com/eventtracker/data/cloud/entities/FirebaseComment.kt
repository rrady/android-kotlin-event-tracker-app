package com.eventtracker.data.cloud.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.Timestamp

import com.eventtracker.domain.models.Comment

@IgnoreExtraProperties
data class FirebaseComment (
    @DocumentId
    val id: String,
    val eventId: String,
    val attachmentId: String,
    val text: String,
    val createdAt: Timestamp
) {
    @Exclude
    fun toComment(): Comment {
        return Comment(id, eventId, attachmentId, text, createdAt.toDate())
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "eventId" to eventId,
            "attachmentId" to attachmentId,
            "text" to text,
            "createdAt" to createdAt
        )
    }

    companion object {
        fun fromComment(comment: Comment): FirebaseComment {
            return FirebaseComment(comment.id, comment.eventId, comment.attachmentId, comment.text, Timestamp(comment.createdAt))
        }
    }
}