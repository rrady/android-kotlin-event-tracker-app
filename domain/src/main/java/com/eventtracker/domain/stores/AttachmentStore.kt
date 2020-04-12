package com.eventtracker.domain.stores

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Attachment

interface AttachmentStore {
    fun getAttachment(id: String): ResultWrapper<Attachment?, Exception>
    fun saveAttachment(attachment: Attachment): ResultWrapper<Unit, Exception>
}