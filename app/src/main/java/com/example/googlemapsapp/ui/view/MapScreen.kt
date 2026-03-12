package com.example.googlemapsapp.ui.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.snapping.SnapPosition.Center.position
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlemapsapp.ui.model.Marker
import com.example.googlemapsapp.ui.navigation.Destination
import com.example.googlemapsapp.ui.viewmodel.MyViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState
import kotlin.toString

@SuppressLint("UnrememberedMutableState", "ViewModelConstructorInComposable")
@Composable
fun MapsScreen(modifier: Modifier = Modifier, navController: NavController) {
    val newMarkerList by MyViewModel().markerList.collectAsStateWithLifecycle(emptyList<Marker>())
    val viewModel = MyViewModel()
    val itb = LatLng(41.4534225, 2.1837151)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itb, 17f)
    }
    Column(modifier.fillMaxSize()) {
        GoogleMap(modifier.fillMaxSize(), cameraPositionState = cameraPositionState, onMapClick = {
            Log.d("MAP CLICKED", it.toString())
        }, onMapLongClick = { LatLng ->
            Log.d("MAP CLICKED LONG", LatLng.toString())
            viewModel.SetCoordinates(LatLng)
            Log.d("COORDINATES SET", viewModel.coordinates.toString())
            navController.navigate(Destination.Marker(LatLng.latitude.toString(), LatLng.longitude.toString()))
        }) {
            newMarkerList.forEach{position ->
                Marker(
                    state = rememberUpdatedMarkerState(LatLng(position.latitude, position.longitude)),
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
