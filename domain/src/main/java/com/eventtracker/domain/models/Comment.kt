package com.eventtracker.domain.models

import java.util.*

data class Comment (
    val id: String,
    val event: Event,
    val attachments: List<Attachment>,
    val replies: List<Reply>,
    val userId: String,
    val text: String,
    val createdAt: Date
)