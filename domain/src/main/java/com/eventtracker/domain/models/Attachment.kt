package com.eventtracker.domain.models

import com.eventtracker.domain.enums.AttachmentType

data class Attachment (
    val id: String,
    val contentUri: String,
    val type: AttachmentType
)