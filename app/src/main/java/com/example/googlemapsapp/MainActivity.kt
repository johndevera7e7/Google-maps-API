package com.example.googlemapsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.googlemapsapp.navigation.AppNavHost
import com.example.googlemapsapp.ui.theme.GoogleMapsAppTheme
import com.example.googlemapsapp.viewmodel.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel by viewModels<MyViewModel>()
        enableEdgeToEdge()
        setContent {
            GoogleMapsAppTheme {
                val navController = rememberNavController()
                myViewModel.updateMarkerList()
                Log.d(
                    "MAIN ACTIVITY",
                    "Marker list updated: ${myViewModel.markerList.value.count()} markers"
                )
                AppNavHost(navController = navController)
            }
        }
    }
}