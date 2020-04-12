package com.eventtracker.data.cloud.entities

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

import com.eventtracker.domain.enums.AttachmentType
import com.eventtracker.domain.models.Attachment

@IgnoreExtraProperties
data class FirebaseAttachment (
    @DocumentId
    val id: String,
    val contentUri: String,
    val type: String
) {
    @Exclude
    fun toAttachment(): Attachment {
        return Attachment(id, contentUri, AttachmentType.valueOf(type))
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "contentUri" to contentUri,
            "type" to type
        )
    }

    companion object {
        fun fromAttachment(attachment: Attachment): FirebaseAttachment {
            return FirebaseAttachment(attachment.id, attachment.contentUri, attachment.type.toString())
        }
    }
}