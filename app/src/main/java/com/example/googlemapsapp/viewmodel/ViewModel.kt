package com.example.googlemapsapp.viewmodel

import android.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlemapsapp.model.Marker
import com.example.googlemapsapp.repository.MarkerRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val repository = MarkerRepository()

    var coordinates: LatLng by mutableStateOf(LatLng(0.0, 0.0))
        private set
    private val _markerList = MutableStateFlow<List<Marker>>(emptyList())

    init {
        loadMarkers()
    }

    private fun loadMarkers() {
        viewModelScope.launch {
            _markerList.value = repository.getMarkers()
        }
    }
    var markerList: StateFlow<List<Marker>> = _markerList

    fun SetCoordinates(value: LatLng) {
        coordinates = value
    }

    fun AddNewMarker(title: String, description: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            MarkerRepository().addMarker(
                title = title,
                description = description,
                latitude = latitude,
                longitude = longitude
            )
        }
    }

    fun updateMarkerList(){
        viewModelScope.launch() {
            markerList = MutableStateFlow(repository.getMarkers())
        }
    }

    fun deleteMarker(title: R.string) {
        viewModelScope.launch {
            repository.getId(title = title.toString())?.let { id ->
                repository.deleteMarker(id)
            }
            updateMarkerList()
        }
    }
}