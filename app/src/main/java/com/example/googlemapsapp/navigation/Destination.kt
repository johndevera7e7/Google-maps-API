package com.example.googlemapsapp.navigation

import kotlinx.serialization.Serializable

sealed class Destination() {
    @Serializable
    object Map : Destination()

    @Serializable
    object List : Destination()

    @Serializable
    data class marker(
        val lat: String,
        val lng: String
    ) : Destination()

    @Serializable
    data class Detail(
        val Marker_id: Int?
    ) : Destination()
}