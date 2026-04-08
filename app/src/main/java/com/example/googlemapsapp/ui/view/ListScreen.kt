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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.googlemapsapp.ui.model.Marker
import com.example.googlemapsapp.ui.viewmodel.MyViewModel

@Composable
fun ListScreen(viewModel: MyViewModel, onMarkerClick:(Marker) -> Unit) {
    val markerList by viewModel.markerList.collectAsStateWithLifecycle(emptyList<Marker>())
    Log.d("MARKER LIST", "${markerList.count()}")
    LazyColumn(Modifier.fillMaxSize()) {
        items(markerList, key = { currentMarker -> currentMarker.id!! }) {
            Row(
                Modifier.fillMaxWidth()
            ) {
                Card(
                    Modifier.fillMaxSize()
                        .clickable{},
                    border = BorderStroke(2.dp, Color.LightGray),
                ) {
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.fillMaxSize()) {
                            Text(text = "Marker: ${it.title}")
                            Text(text = "Latitude: ${it.latitude}")
                            Text(text = "Longitude: ${it.longitude}")
                        }
                    }
                }
            }
            Spacer(Modifier.height(15.dp))
        }
    }
}