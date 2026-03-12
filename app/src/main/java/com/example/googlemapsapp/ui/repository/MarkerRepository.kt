package com.example.googlemapsapp.ui.repository

import com.example.googlemapsapp.ui.model.Marker
import com.example.googlemapsapp.ui.network.SupabaseClient

import io.github.jan.supabase.postgrest.postgrest


class MarkerRepository {
    private val taula = SupabaseClient.client.postgrest["markers"]

    suspend fun addMarker(title: String,description: String ,latitude: Double, longitude: Double) {
        val newMarker = Marker(title = title, latitude = latitude, longitude = longitude, description = description?: "No description provided.")
        taula.insert(newMarker)
    }

    suspend fun getMarkers(): List<Marker> {
        return taula.select().decodeList<Marker>()
    }

    suspend fun deleteMarker(id: Int) {
        taula.delete {
            filter {
                eq("id", id)
            }
        }
    }

    suspend fun getId(title: String): Int? {
        val marker = taula.select {
            filter {
                eq("title", title)
            }
        }.decodeList<Marker>().firstOrNull()
        return marker?.id
    }

    companion object
}