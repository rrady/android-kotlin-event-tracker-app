package com.eventtracker.domain.models

data class Host (
    var id: String,
    val name: String,
    val description: String,
    val info: String,
    val site: String,
    val phone: String,
    val email: String,
    val avatarUri: String
)