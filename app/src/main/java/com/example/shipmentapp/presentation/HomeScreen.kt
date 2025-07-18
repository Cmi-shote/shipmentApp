package com.example.shipmentapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top section: Profile, location, search bar, notification
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
//            .background(MaterialTheme.colorScheme.primary)
        ) {
            // TODO: Add profile image, location, search bar, notification icon
        }
        // Tracking card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
        ) {
            // TODO: Add tracking info as shown in the design
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Available vehicles
        AvailableVehiclesCard()
    }
}

@Composable
fun AvailableVehiclesCard() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Available vehicles",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VehicleCard(title = "Ocean freight", subtitle = "International", imageRes = null)
            VehicleCard(title = "Cargo freight", subtitle = "Reliable", imageRes = null)
            VehicleCard(title = "Air freight", subtitle = "International", imageRes = null)
        }
    }
}

@Composable
fun VehicleCard(title: String, subtitle: String, imageRes: Int?) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder for image
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color(0xFFEFEFEF), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Uncomment and use painterResource(imageRes) when you have images
            // Image(painter = painterResource(id = imageRes), contentDescription = title)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = subtitle, color = Color.Gray, fontSize = 13.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}