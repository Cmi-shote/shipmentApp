package com.example.shipmentapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shipmentapp.models.Shipment
import com.example.shipmentapp.models.Vehicles
import com.example.shipmentapp.models.sampleCard
import com.example.shipmentapp.models.vehiclesList
import com.example.shipmentapp.presentation.components.SearchBar
import com.example.shipmentapp.presentation.components.ShipmentCard
import com.example.shipmentapp.presentation.components.VehiclesCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    shipment: Shipment = sampleCard,
    onSearchBarClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .background(color = Color(0xFF543B9C))
                .padding(16.dp)
        ) {
            // TODO: Add tracking info as shown in the design

            Column {
                HomePageToolbar()

                Spacer(modifier = Modifier.height(24.dp))

                SearchBar(
                    onSearchBarClick = onSearchBarClick,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Tracking",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
            )

            Spacer(modifier = Modifier.height(8.dp))

            ShipmentCard(shipment = shipment)

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Available vehicles",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Available vehicles
            AvailableVehiclesCard()
        }
    }
}

@Composable
fun AvailableVehiclesCard(vehicles: List<Vehicles> = vehiclesList) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(vehicles) { vehicle ->
            VehiclesCard(
                title = vehicle.vehicleType,
                subtitle = vehicle.desc,
                image = vehicle.image,
                modifier = Modifier.height(200.dp).width(170.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}