package com.example.shipmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.shipmentapp.navigation.AppNavigation
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        window.statusBarColor = Color(0xFF543B9C).toArgb()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShipmentAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { _ ->
                    AppNavigation(
                        navController = navController
                    )
                }
            }
        }
    }
}