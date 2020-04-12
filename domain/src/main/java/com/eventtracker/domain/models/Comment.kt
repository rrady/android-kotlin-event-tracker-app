package com.eventtracker.domain.models

import java.util.*

data class Comment (
    val id: String,
    val eventId: String,
    val attachmentId: String,
    val text: String,
    val createdAt: Date
)