package com.example.googlemapsapp.navigation

import kotlinx.serialization.Serializable

class Destination {
    sealed class Destination() {
        @Serializable
        object Home : Destination()
        @Serializable
        object Settings : Destination()
        @Serializable
        object About : Destination()
    }
}