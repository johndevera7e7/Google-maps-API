package com.example.googlemapsapp.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.googlemapsapp.ui.model.Marker
import com.example.googlemapsapp.ui.viewmodel.MyViewModel

@Composable
fun ListScreen(viewModel: MyViewModel) {
    val markerList by viewModel.markerList.collectAsStateWithLifecycle(emptyList<Marker>())
    Log.d("MARKER LIST","${markerList.count()}")
    LazyColumn(Modifier.fillMaxSize()) {
        items(markerList, key = { currentMarker -> currentMarker.id!! }) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(75.dp)
            ) {
                Text(text = "${it.title}, ${it.latitude} , ${it.longitude}")
            }
        }
    }
}