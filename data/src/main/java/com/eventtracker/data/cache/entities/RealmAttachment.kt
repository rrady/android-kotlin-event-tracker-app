package com.eventtracker.data.cache.entities

import io.realm.annotations.PrimaryKey
import io.realm.RealmObject

import com.eventtracker.domain.enums.AttachmentType
import com.eventtracker.domain.models.Attachment

open class RealmAttachment (
    @PrimaryKey
    var id: String = "",
    var contentUri: String = "",
    var type: String = ""
): RealmObject() {
    fun toAttachment(): Attachment {
        return Attachment(id, contentUri, AttachmentType.valueOf(type))
    }

    companion object {
        fun fromAttachment(attachment: Attachment): RealmAttachment {
            return RealmAttachment(attachment.id, attachment.contentUri, attachment.type.toString())
        }
    }
}