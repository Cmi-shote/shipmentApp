package com.example.shipmentapp.presentation.results

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

        Spacer(modifier = Modifier.height(60.dp))

        // 3D Package Illustration
        PackageIllustration()

        Spacer(modifier = Modifier.height(60.dp))

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
        modifier = Modifier.size(160.dp),
        contentAlignment = Alignment.Center
    ) {
        // Main box body
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    Color(0xFFB8C5D1), // Light blue-gray
                    RoundedCornerShape(8.dp)
                )
        ) {
            // Top face highlight
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(
                        Color(0xFFD1D8DD), // Lighter shade
                        RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
            )

            // Side panel
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(20.dp)
                    .height(80.dp)
                    .background(Color(0xFF9AA5B0)) // Darker shade
            )

            // Shipping label
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .size(width = 24.dp, height = 16.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(2.dp)
                    )
            ) {
                // Small lines to represent text on label
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(Color(0xFFE5E7EB))
                        )
                    }
                }
            }

            // Fragile/Handle with care dots
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                Color(0xFF6B7280),
                                RoundedCornerShape(50)
                            )
                    )
                }
            }
        }
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
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = estimatedAmount,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF49C38D) // Green color
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = currency,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6B7280) // Gray color
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