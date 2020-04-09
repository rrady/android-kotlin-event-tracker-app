package com.eventtracker.domain.models

data class Host (
    val id: String,
    val address: Address,
    val name: String,
    val description: String,
    val avatarUri: String
)