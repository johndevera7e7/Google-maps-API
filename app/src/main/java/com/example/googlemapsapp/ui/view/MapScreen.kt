package com.example.googlemapsapp.ui.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.googlemapsapp.model.Marker
import com.example.googlemapsapp.navigation.Destination
import com.example.googlemapsapp.viewmodel.MyViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@SuppressLint("UnrememberedMutableState", "ViewModelConstructorInComposable")
@Composable
fun MapsScreen(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = MyViewModel()
    val newMarkerList by viewModel.markerList.collectAsStateWithLifecycle(emptyList<Marker>())
    var itb = LatLng(41.4534225, 2.1837151)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itb, 17f)
    }
    Column(modifier.fillMaxSize()) {
        GoogleMap(modifier.fillMaxSize(), cameraPositionState = cameraPositionState, onMapClick = {
            Log.d("MAP CLICKED", it.toString())
        }, onMapLongClick = { LatLng ->
            itb = LatLng
            Log.d("MAP CLICKED LONG", LatLng.toString())
            viewModel.SetCoordinates(LatLng)
            Log.d("COORDINATES SET", viewModel.coordinates.toString())
            navController.navigate(
                Destination.marker(
                    LatLng.latitude.toString(), LatLng.longitude.toString()
                )
            )
        }) {
            Log.d("MARKERS RETRIEVED", "${newMarkerList.count()} markers")
            newMarkerList.forEach { position ->
                Marker(
                    state = rememberUpdatedMarkerState(
                        LatLng(
                            position.latitude, position.longitude
                        )
                    ),
                    title = "New Marker",
                    snippet = "${position.latitude.toFloat()}, ${position.longitude.toFloat()}"
                )
            }
            Marker(
                state = MarkerState(position = itb), title = "ITB", snippet = "Marker at ITB"
            )
        }
    }
}
