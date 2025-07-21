package com.example.shipmentapp.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.R
import com.example.shipmentapp.models.Shipment
import com.example.shipmentapp.models.Vehicles
import com.example.shipmentapp.models.sampleShipments
import com.example.shipmentapp.models.vehiclesList
import com.example.shipmentapp.presentation.components.BottomNavigationBar
import com.example.shipmentapp.presentation.components.SearchBar
import com.example.shipmentapp.presentation.components.ShipmentCard
import com.example.shipmentapp.presentation.components.VehiclesCard
import com.example.shipmentapp.ui.theme.ShipmentAppTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    shipment: Shipment = sampleShipments[1],
    onSearchBarClick: () -> Unit = {},
    navigateToCalculateScreen: () -> Unit = {},
    navigateToShipmentHistoryScreen: () -> Unit = {}
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    // Animation states
    var isTopSectionVisible by remember { mutableStateOf(false) }
    var isContentVisible by remember { mutableStateOf(false) }

    // Trigger animations with staggered delays
    LaunchedEffect(Unit) {
        delay(100) // Small initial delay
        isTopSectionVisible = true
        delay(200) // Delay between top section and content
        isContentVisible = true
        selectedTabIndex = 0
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            // Animated TopBar - slides down and fades in
            AnimatedVisibility(
                visible = isTopSectionVisible,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = 600)
                ) + slideInVertically(
                    animationSpec = tween(durationMillis = 600),
                    initialOffsetY = { -it } // Slide from above (negative offset)
                )
            ) {
                HomePageToolbar()
            }
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                // Animated Search Bar Section - slides down and fades in
                AnimatedVisibility(
                    visible = isTopSectionVisible,
                    enter = fadeIn(
                        animationSpec = tween(durationMillis = 600, delayMillis = 100)
                    ) + slideInVertically(
                        animationSpec = tween(durationMillis = 600, delayMillis = 100),
                        initialOffsetY = { -it / 2 } // Slide from above with less distance
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = colorResource(id = R.color.app_color_purple))
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "toolbar"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                    ) {
                        SearchBar(
                            onSearchBarClick = onSearchBarClick,
                            readOnly = true,
                            modifier = Modifier
                                .padding(16.dp)
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = "searchK"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 1000)
                                    }
                                )
                        )
                    }
                }

                // Animated Content Section - slides up and fades in
                AnimatedVisibility(
                    visible = isContentVisible,
                    enter = fadeIn(
                        animationSpec = tween(durationMillis = 700)
                    ) + slideInVertically(
                        animationSpec = tween(durationMillis = 700),
                        initialOffsetY = { it / 2 } // Slide from below (positive offset)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "Tracking",
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Medium,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        ShipmentCard(shipment = shipment)

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "Available vehicles",
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Medium,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Available vehicles
                        AvailableVehiclesCard()
                    }
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
        itemsIndexed(vehicles) { index, vehicle ->
            var isVisible by remember { mutableStateOf(false) }

            // Trigger animation with staggered delay
            LaunchedEffect(Unit) {
                delay(index * 100L) // 100ms delay between each item
                isVisible = true
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth }, // Start from right
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
            ) {
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
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    ShipmentAppTheme {
//        HomeScreen()
//    }
//}