package com.example.shipmentapp.presentation.shipmentHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.R
import com.example.shipmentapp.models.Shipment
import com.example.shipmentapp.models.ShipmentStatus
import com.example.shipmentapp.models.TabItem
import com.example.shipmentapp.models.sampleShipments
import com.example.shipmentapp.presentation.components.CustomToolbar
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

@Composable
fun ShipmentHistoryScreen(
    onBackClick: () -> Unit = {},
    shipments: List<Shipment> = sampleShipments
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        TabItem("All", 12),
        TabItem("Completed", 5),
        TabItem("In progress", 3),
        TabItem("Pending Order", 4),
        TabItem("Cancelled", 0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top Bar
        CustomToolbar(
            title = "Shipment History"
        )

        // Custom Tab Row
        Box(modifier = Modifier.background(colorResource(id = R.color.app_color_purple))){
            ShipmentTabRow(
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )
        }

        // Content Area
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Shipments",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }

                items(shipments) { shipment ->
                    ShipmentCard(shipment = shipment)
                }
            }
        }
    }
}

// Tab Row Composable
@Composable
fun ShipmentTabRow(
    tabs: List<TabItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.White,
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
                        color = if (selectedTabIndex == index) Color.White else Color.White.copy(alpha = 0.7f),
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
                                    color = if (selectedTabIndex == index) colorResource(id = R.color.app_color_orange) else Color.LightGray.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(
                                        16.dp
                                    )
                                )
                        ) {
                            Text(
                                text = tab.count.toString(),
                                color = if (selectedTabIndex == index) Color.White else Color.LightGray.copy(alpha = 0.7f),
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

// Shipment Card Composable
@Composable
fun ShipmentCard(
    shipment: Shipment,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
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
                    .size(48.dp)
                    .background(
                        Color(0xFFF3F4F6),
                        RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📦",
                    fontSize = 24.sp
                ) //todo: fix icon
            }
        }
    }
}

// Status Icon Composable
@Composable
fun ShipmentStatusIcon(modifier: Modifier= Modifier, status: ShipmentStatus) {

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
            Color(0xFFDCFCE7),
            Icons.Default.CheckCircleOutline,
            "completed"
        )
        ShipmentStatus.PENDING -> Triple(
            colorResource(id = R.color.app_color_orange),
            Icons.Default.History,
            "pending"
        )
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF6F6F6))
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Rotating arrow icon (you can replace with your custom arrow)
        Icon(
            imageVector = icon,
            contentDescription = "In progress",
            modifier = Modifier
                .size(16.dp)
                .rotate(90f),
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