package com.example.googlemapsapp.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googlemapsapp.model.Marker
import com.example.googlemapsapp.navigation.Destination
import com.example.googlemapsapp.viewmodel.MyViewModel

@Composable
fun DetailScreen(
    marker: Destination.Detail,
    onBack: () -> Unit,
    viewModel: MyViewModel
) {
    val markerList by viewModel.markerList.collectAsStateWithLifecycle()
    var noMarkerName by remember { mutableStateOf(false) }
    var current_marker = markerList.find { it.id == marker.Marker_id }
    var newName: String by remember { mutableStateOf(current_marker?.title ?: "") }
    var newDesc: String by remember { mutableStateOf(current_marker?.description ?: "") }

    Log.d("DetailScreen", "Marker Name: ${current_marker?.id}")
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = newName,
            onValueChange = { newName = it },
            label = { Text("Marker Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = newDesc,
            onValueChange = { newDesc = it },
            label = { Text("Description") },
            modifier = Modifier.height(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Latitude: ${current_marker?.latitude}")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Longitude: ${current_marker?.longitude}")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .width(200.dp),
            onClick = {
                if (newName == "") {
                    noMarkerName = true
                } else {
                    viewModel.EditMarker(
                        id = current_marker?.id ?: 0,
                        title = newName,
                        description = newDesc,
                        latitude = current_marker?.latitude?: 0.0,
                        longitude = current_marker?.longitude?: 0.0
                    )
                    viewModel.updateMarkerList()
                }
            }
        ) {
            Text("Save Marker")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .width(200.dp),
            onClick = onBack
        ) {
            Text("Back")
        }
        if (noMarkerName) {
            Text("Please provide a name for the marker.", color = Color.Red)
        } else {
            Text("Marker saved successfully!", color = Color.Green)
        }
    }
}