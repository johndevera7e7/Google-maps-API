package com.example.googlemapsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.googlemapsapp.ui.theme.GoogleMapsAppTheme
import com.example.googlemapsapp.view.MapsScreen
import com.example.googlemapsapp.viewmodel.MyViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel by viewModels<MyViewModel>()
        enableEdgeToEdge()
        setContent {
            GoogleMapsAppTheme {
                MapsScreen()
            }
        }
    }
}
