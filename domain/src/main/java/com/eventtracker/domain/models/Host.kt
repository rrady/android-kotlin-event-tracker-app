package com.eventtracker.domain.models

data class Host (
    val id: String,
    val ownerId: String,
    val name: String,
    val description: String,
    val info: String,
    val site: String,
    val phone: String,
    val email: String,
    var avatarUri: String
)