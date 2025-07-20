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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shipmentapp.R
import com.example.shipmentapp.models.Shipment
import com.example.shipmentapp.models.Vehicles
import com.example.shipmentapp.models.sampleShipments
import com.example.shipmentapp.models.vehiclesList
import com.example.shipmentapp.presentation.components.BottomNavigationBar
import com.example.shipmentapp.presentation.components.SearchBar
import com.example.shipmentapp.presentation.components.ShipmentCard
import com.example.shipmentapp.presentation.components.VehiclesCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    shipment: Shipment = sampleShipments[0],
    onSearchBarClick: () -> Unit = {},
    navigateToCalculateScreen: () -> Unit = {},
    navigateToShipmentHistoryScreen: () -> Unit = {}
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            HomePageToolbar()
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)){
                Box(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.app_color_purple))
                ) {
                    SearchBar(
                        onSearchBarClick = onSearchBarClick,
                        readOnly = true,
                        modifier = Modifier.padding(16.dp)
                    )
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
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    selectedTabIndex = index
                    // Handle navigation based on selected tab
                    when (index) {
                        0 -> { /* Already on Home - no navigation needed */ }
                        1 -> navigateToCalculateScreen()
                        2 -> navigateToShipmentHistoryScreen()
                        3 -> { /* not shown in video */ }
                    }
                }
            )
        }
    )
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
                modifier = Modifier
                    .height(200.dp)
                    .width(170.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}