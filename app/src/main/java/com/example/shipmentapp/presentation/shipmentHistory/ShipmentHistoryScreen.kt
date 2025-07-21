package com.example.shipmentapp.presentation.shipmentHistory

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shipmentapp.R
import com.example.shipmentapp.models.Shipment
import com.example.shipmentapp.models.ShipmentStatus
import com.example.shipmentapp.models.TabItem
import com.example.shipmentapp.models.sampleShipments
import com.example.shipmentapp.models.tabs
import com.example.shipmentapp.presentation.components.CustomToolbar
import com.example.shipmentapp.ui.theme.ShipmentAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun ShipmentHistoryScreen(
    onBackClick: () -> Unit = {},
    shipments: List<Shipment> = sampleShipments,
    viewModel: ShipmentHistoryViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var isCardVisible by remember { mutableStateOf(false) }
    var isTabRowVisible by remember { mutableStateOf(false) }
    var isToolbarVisible by remember { mutableStateOf(false) }

    val systemUiController = rememberSystemUiController()
    val statusBarColor = colorResource(id = R.color.app_color_purple)

    // Set the status bar color when the screen is launched
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false // set to true if your text/icons are dark
        )
        delay(100)
        isToolbarVisible = true
        delay(100) // Small delay before tab row
        isTabRowVisible = true
        delay(200) // Small delay before cards start animating
        isCardVisible = true
    }

    LaunchedEffect(uiState.selectedTabIndex) {
        isCardVisible = false // Start fade out
        delay(500)
        isCardVisible = true // Start fade/slide in
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomToolbar(
                title = "Shipment History",
                onBackClick = onBackClick
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    // Custom Tab Row
                    item {
                        Box(
                            modifier = Modifier.background(colorResource(id = R.color.app_color_purple))
                                .padding()
                        ) {
                            ShipmentTabRow(
                                tabs = tabs,
                                selectedTabIndex = selectedTabIndex,
                                onTabSelected = {
                                    selectedTabIndex = it
                                    viewModel.onTabSelected(it)
                                },
                                isTabRowVisible = isTabRowVisible
                            )
                        }
                    }

                    item {
                        Text(
                            text = "Shipments",
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp),
                        )
                    }

                    itemsIndexed(uiState.filteredShipments) { index, shipment ->
                        AnimatedVisibility(
                            visible = isCardVisible,
                            enter = fadeIn(
                                animationSpec = tween(
                                    durationMillis = 600,
                                    delayMillis = index * 100 // Stagger the animation
                                )
                            ) + slideInVertically(
                                animationSpec = tween(
                                    durationMillis = 600,
                                    delayMillis = index * 100
                                ),
                                initialOffsetY = { it / 4 } // Slide in from 25% down
                            )
                        ) {
                            ShipmentCard(
                                shipment = shipment,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 16.dp
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White.copy(alpha = 0.8f),
                                    Color.White
                                ),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )
            }
        }
    )
}

@Composable
fun ShipmentTabRow(
    tabs: List<TabItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isTabRowVisible: Boolean = true
) {
    AnimatedVisibility(
        visible = isTabRowVisible,
        enter = slideInHorizontally(
            initialOffsetX = { -it }, // Slide in from left (use -it for left, it for right)
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
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Color.White,
            edgePadding = 16.dp, // This controls the starting position
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .height(3.dp)
                            .background(
                                colorResource(id = R.color.app_color_orange),
                            )
                    )
                }
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 12.dp)
                    ) {
                        Text(
                            text = tab.title,
                            color = if (selectedTabIndex == index) Color.White else Color.White.copy(
                                alpha = 0.7f
                            ),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Medium else FontWeight.Normal
                        )

                        if (tab.count > 0) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(20.dp)
                                    .background(
                                        color = if (selectedTabIndex == index) colorResource(id = R.color.app_color_orange) else Color.LightGray.copy(
                                            alpha = 0.2f
                                        ),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Text(
                                    text = tab.count.toString(),
                                    color = if (selectedTabIndex == index) Color.White else Color.LightGray.copy(
                                        alpha = 0.7f
                                    ),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Shipment Card Composable
@Composable
fun ShipmentCard(
    shipment: Shipment,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {

                // Status Icon
                ShipmentStatusIcon(status = shipment.status)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = shipment.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = shipment.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = shipment.amount,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(id = R.color.app_color_purple)
                    )

                    Text(
                        text = shipment.date,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }

            // Package Icon
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.box1),
                    contentDescription = "Package"
                )
            }
        }
    }
}

// Status Icon Composable
@Composable
fun ShipmentStatusIcon(modifier: Modifier = Modifier, status: ShipmentStatus) {

    val (color, icon, text) = when (status) {
        ShipmentStatus.LOADING -> Triple(
            Color(0xFF4A87B6),
            ImageVector.vectorResource(R.drawable.avg_pace),
            "loading"
        )

        ShipmentStatus.IN_PROGRESS -> Triple(
            Color(0xFF49C38D),
            Icons.Default.Sync,
            "in-progress"
        )

        ShipmentStatus.COMPLETED -> Triple(
            Color.LightGray,
            Icons.Default.CheckCircleOutline,
            "completed"
        )

        ShipmentStatus.PENDING -> Triple(
            colorResource(id = R.color.app_color_orange),
            Icons.Default.History,
            "pending"
        )
        else -> Triple(Color.White, Icons.Default.Sync, "loading")
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF6F6F6))
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "In progress",
            modifier = Modifier
                .size(16.dp)
                .rotate(if (status == ShipmentStatus.IN_PROGRESS) 270f else 0f),
            tint = color
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = color
        )
    }


//    Box(
//        modifier = Modifier
//            .size(40.dp)
//            .background(backgroundColor, CircleShape),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = icon,
//            color = iconColor,
//            fontSize = 18.sp
//        )
//    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentHistoryScreenPreview() {
    ShipmentAppTheme {
        ShipmentHistoryScreen()
    }
}