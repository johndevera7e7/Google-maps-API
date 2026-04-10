package com.example.googlemapsapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.googlemapsapp.layout.MainScaffold
import com.example.googlemapsapp.ui.view.AddMarkerScreen
import com.example.googlemapsapp.ui.view.DetailScreen
import com.example.googlemapsapp.ui.view.ListScreen
import com.example.googlemapsapp.ui.view.MapsScreen
import com.example.googlemapsapp.viewmodel.MyViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(navController: NavHostController, viewModel: MyViewModel) {
    MainScaffold(navController) {
        NavHost(
            navController = navController,
            startDestination = Destination.Map
        ) {
            composable<Destination.Map> { MapsScreen(navController = navController) }

            composable<Destination.List> {
                ListScreen(viewModel, onMarkerClick = { marker_id ->
                    navController.navigate(Destination.Detail(Marker_id = marker_id))
                })
            }
            composable<Destination.marker> { backStackEntry ->
                val marker = backStackEntry.toRoute<Destination.marker>()
                AddMarkerScreen(navController = navController, marker.lat, marker.lng)
            }
            composable<Destination.Detail> { backStackEntry ->
                val marker = backStackEntry.toRoute<Destination.Detail>()
                DetailScreen(viewModel = viewModel, marker = marker, onBack = { navController.popBackStack() })
            }
        }
    }
}