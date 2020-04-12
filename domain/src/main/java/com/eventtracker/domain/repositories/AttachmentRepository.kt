package com.eventtracker.domain.repositories

import com.eventtracker.domain.ResultWrapper
import com.eventtracker.domain.models.Attachment

interface AttachmentRepository {
    suspend fun getAttachment(id: String): ResultWrapper<Attachment?, Exception>
    suspend fun addAttachment(attachment: Attachment): ResultWrapper<Unit, Exception>
    suspend fun updateAttachment(attachment: Attachment): ResultWrapper<Unit, Exception>
    suspend fun deleteAttachment(id: String): ResultWrapper<Unit, Exception>
}