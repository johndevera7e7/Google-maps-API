package com.example.googlemapsapp.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.snapping.SnapPosition.Center.position
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapsScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        val itb = LatLng(41.4534225, 2.1837151)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(itb, 17f)
        }
        GoogleMap(modifier.fillMaxSize(), cameraPositionState = cameraPositionState, onMapClick = {
            Log.d("MAP CLICKED", it.toString())
        }, onMapLongClick = {
            Log.d("MAP CLICKED LONG", it.toString())
        }) {
            Marker(
                state = MarkerState(position = itb), title = "ITB", snippet = "Marker at ITB"
            )
        }
    }
}
