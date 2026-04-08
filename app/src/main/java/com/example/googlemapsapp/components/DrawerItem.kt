package com.example.googlemapsapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.googlemapsapp.navigation.Destination

enum class DrawerItem(
    val icon: ImageVector,
    val text: String,
    val route: Destination
) {
    MAP(Icons.Default.Map, "Map", Destination.Map),
    LIST(Icons.Default.List, "Marcadors", Destination.List)

}