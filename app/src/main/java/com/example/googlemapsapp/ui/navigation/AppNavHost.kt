package com.example.googlemapsapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.googlemapsapp.ui.layout.MainScaffold
import com.example.googlemapsapp.ui.view.AddMarkerScreen
import com.example.googlemapsapp.ui.view.ListScreen
import com.example.googlemapsapp.ui.view.MapsScreen
import com.example.googlemapsapp.ui.viewmodel.MyViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(navController: NavHostController) {
    MainScaffold(navController) {
        NavHost(
            navController = navController,
            startDestination = Destination.Map
        ) {
            composable<Destination.Map> { MapsScreen(navController = navController) }
            composable<Destination.List> { ListScreen(MyViewModel()) }
            composable<Destination.Marker> { backStackEntry ->
                val marker = backStackEntry.toRoute<Destination.Marker>()
                AddMarkerScreen(navController = navController, marker.lat, marker.lng) }
        }
    }
}