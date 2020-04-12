package com.eventtracker.domain.models

import java.util.*

data class Reply (
    val id: String,
    val commentId: String,
    val text: String,
    val createdAt: Date
)