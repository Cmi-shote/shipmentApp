package com.example.shipmentapp.presentation.results

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.shipmentapp.R
import com.example.shipmentapp.presentation.components.CustomButton

@Composable
fun ResultsScreen(
    estimatedAmount: String = "$1452",
    currency: String = "USD",
    onBackToHome: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // MoveMate Logo and Title
        MoveMateHeader()

        Spacer(modifier = Modifier.height(24.dp))

        // 3D Package Illustration
        PackageIllustration()

        // Estimation Content
        EstimationContent(
            estimatedAmount = estimatedAmount,
            currency = currency
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(onClick = onBackToHome, text = "Back to Home")

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun MoveMateHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MoveMate",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = colorResource(R.color.app_color_purple)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.delivery_truck),
            contentDescription = "MoveMate Logo",
            tint = colorResource(R.color.app_color_orange),
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
private fun PackageIllustration() {
    // 3D-style package box using CSS-like styling with Compose
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ) {
        // Main box body
        Image(
            painter = painterResource(R.drawable.box1),
            contentDescription = "Package",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Composable
private fun EstimationContent(
    estimatedAmount: String,
    currency: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total Estimated Amount",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF374151),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = estimatedAmount,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF49C38D) // Green color
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = currency,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF49C38D),
                modifier = Modifier.align(Alignment.Bottom)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "This amount is estimated this will vary\nif you change your location or weight",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoveMateEstimationScreenPreview() {
    MaterialTheme {
        ResultsScreen()
    }
}