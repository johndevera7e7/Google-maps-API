package com.example.googlemapsapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Marker (
    val id: Int? = null,
    val title: String,
    val description: String?,
    val latitude: Double,
    val longitude: Double
)