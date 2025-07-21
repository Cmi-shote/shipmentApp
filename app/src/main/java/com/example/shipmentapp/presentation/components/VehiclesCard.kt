package com.example.shipmentapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.R
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

@Composable
fun VehiclesCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    image: Int,
    backgroundColor: Color = Color.White,
) {
    Box(
        modifier =
            modifier.size(200.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(16.dp),
                ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
        ) {
            // Title and Subtitle
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.Start),
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Start),
                )
            }

            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

// Preview
@Preview
@Composable
fun VehicleCardPreview() {
    ShipmentAppTheme {
        VehiclesCard(
            modifier = Modifier.padding(16.dp),
            title = "Ocean freight",
            subtitle = "International",
            image = R.drawable.boat,
            backgroundColor = Color.White,
        )
    }
}
