package com.eventtracker.domain.models

data class Host (
    var id: String,
    val name: String,
    val description: String,
    val avatarUri: String
)