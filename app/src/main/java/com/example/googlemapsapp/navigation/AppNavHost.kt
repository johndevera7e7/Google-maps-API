package com.example.googlemapsapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.googlemapsapp.layout.MainScaffold
import com.example.googlemapsapp.model.Marker
import com.example.googlemapsapp.ui.view.AddMarkerScreen
import com.example.googlemapsapp.ui.view.ListScreen
import com.example.googlemapsapp.ui.view.MapsScreen
import com.example.googlemapsapp.viewmodel.MyViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(navController: NavHostController) {
    var selectedMarker by remember() { mutableStateOf<Marker?>(null) }
    MainScaffold(navController) {
        NavHost(
            navController = navController,
            startDestination = Destination.Map
        ) {
            composable<Destination.Map> { MapsScreen(navController = navController) }
            composable<Destination.List> {
                ListScreen(MyViewModel(), onMarkerClick = {Marker -> selectedMarker = Marker}) }
            composable<Destination.Marker> { backStackEntry ->
                val marker = backStackEntry.toRoute<Destination.Marker>()
                AddMarkerScreen(navController = navController, marker.lat, marker.lng) }
        }
    }
}