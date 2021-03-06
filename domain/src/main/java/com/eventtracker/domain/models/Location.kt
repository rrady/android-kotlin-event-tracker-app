package com.eventtracker.domain.models

data class Location (
    val id: String,
    val country: String,
    val city: String,
    val street: String,
    val number: String,
    val zipCode: String,
    val latitude: Double,
    val longitude: Double
)