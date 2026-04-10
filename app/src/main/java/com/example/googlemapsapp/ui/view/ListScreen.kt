package com.example.googlemapsapp.ui.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.googlemapsapp.model.Marker
import com.example.googlemapsapp.viewmodel.MyViewModel
import com.google.maps.android.compose.internal.LocalGoogleMapsInitializer

@Composable
fun ListScreen(viewModel: MyViewModel, onMarkerClick: (Int) -> Unit) {
    val markerList by viewModel.markerList.collectAsStateWithLifecycle(emptyList<Marker>())
    Log.d("MARKER LIST BROSKI", "${markerList.count()}")
    LazyColumn(Modifier.fillMaxSize()) {
        items(markerList) {
            MarkerItem(marker = it, onItemClick = onMarkerClick)
        }
    }
}

@Composable
fun MarkerItem(onItemClick:(Int) -> Unit, marker: Marker){
    Row(
        Modifier.fillMaxWidth()
    ) {
        Card(
            Modifier.fillMaxSize()
                .clickable{onItemClick(marker.id?: 0)},
            border = BorderStroke(2.dp, Color.LightGray),
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.fillMaxSize()) {
                    Text(text = "Marker: ${marker.title}")
                    Text(text = "Latitude: ${marker.latitude}")
                    Text(text = "Longitude: ${marker.longitude}")
                }
            }
        }
    }
    Spacer(Modifier.height(15.dp))
}