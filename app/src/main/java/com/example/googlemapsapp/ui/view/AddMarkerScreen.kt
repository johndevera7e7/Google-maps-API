package com.example.googlemapsapp.ui.view

import android.annotation.SuppressLint
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
import androidx.navigation.NavController
import com.example.googlemapsapp.navigation.Destination
import com.example.googlemapsapp.viewmodel.MyViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AddMarkerScreen(navController: NavController, lat: String, lng: String) {
    val viewModel = MyViewModel()
    val Lat = lat.toDouble()
    val Lng = lng.toDouble()
    var noMarkerName by remember { mutableStateOf(true) }
    var markerName by remember { mutableStateOf("") }
    var markerDesc by remember { mutableStateOf("No description provided.") }
    Log.d("ADD MARKER SCREEN", "Coordinates: ${Lat}, ${Lng}")
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = markerName,
            onValueChange = { markerName = it },
            label = { Text("Marker Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = { markerDesc = it },
            label = { Text("Description") },
            modifier = Modifier.height(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Latitude: ${Lat}")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Longitude: ${Lng}")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .width(200.dp),
            onClick = {
                if (markerName == "") {
                    noMarkerName = true
                } else {
                    noMarkerName = false
                    viewModel.AddNewMarker(
                        title = markerName,
                        description = markerDesc,
                        latitude = Lat,
                        longitude = Lng
                    )
                    viewModel.updateMarkerList()
                    navController.navigate(Destination.Map)
                }
            }
        ) {
            Text("Save Marker")
        }
        if (noMarkerName) {
            Text("Please provide a name for the marker.", color = Color.Red)
        } else {
            Text("Marker saved successfully!", color = Color.Green)
        }
    }
}
