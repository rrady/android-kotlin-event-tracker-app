package com.eventtracker.domain.models

import java.util.*

data class Event (
    val id: String,
    val hostId: String,
    val locationId: String,
    val name: String,
    val description: String,
    val createdAt: Date,
    val beginDate: Date,
    val endDate: Date,
    val photoUri: String
)