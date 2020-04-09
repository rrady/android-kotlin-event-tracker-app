package com.eventtracker.domain.models

import java.util.*

data class Event (
    val id: String,
    val host: Host,
    val address: Address,
    val name: String,
    val description: String,
    val createdAt: Date,
    val beginDate: Date,
    val endDate: Date,
    val photoUri: String
)