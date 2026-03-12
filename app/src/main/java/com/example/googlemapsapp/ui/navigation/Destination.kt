package com.example.googlemapsapp.ui.navigation

import kotlinx.serialization.Serializable


sealed class Destination() {
    @Serializable
    object Map : Destination()

    @Serializable
    object List : Destination()

    @Serializable
    data class Marker(
        val lat: String,
        val lng: String
    ) : Destination()
}
