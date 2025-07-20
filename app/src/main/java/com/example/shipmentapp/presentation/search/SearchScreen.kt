package com.example.shipmentapp.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shipmentapp.R
import com.example.shipmentapp.models.Order
import com.example.shipmentapp.models.sampleOrders
import com.example.shipmentapp.presentation.components.SearchBar
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    orders: List<Order> = sampleOrders) {
    LazyColumn(modifier = modifier) {
        item {
            Box(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.app_color_purple))
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
            ) {
                SearchBar(
                    showBackIcon = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column {
                    orders.forEachIndexed { index, order ->
                        if (index != 0) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 24.dp),
                                color = Color.LightGray.copy(alpha = 0.5f)
                            )
                        }
                        ShipmentInfoCard(
                            order = order
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen(modifier: Modifier = Modifier) {
    ShipmentAppTheme {
        SearchScreen()
    }
}