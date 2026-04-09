package com.example.googlemapsapp.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.googlemapsapp.navigation.Destination

@Composable
fun DetailScreen(
    marker: Destination.Detail,
    onBack: () -> Unit
) {
    Log.d("DetailScreen", "Marker ID: ${marker.Marker.id}")
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Detail Screen, Marker Name: ${marker.Marker.title}")

    }
}