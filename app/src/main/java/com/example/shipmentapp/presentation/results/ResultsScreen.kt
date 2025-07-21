package com.example.shipmentapp.presentation.results

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.R
import com.example.shipmentapp.presentation.components.CustomButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun ResultsScreen(
    estimatedAmount: String = "$1452",
    currency: String = "USD",
    onBackToHome: () -> Unit = {},
) {
    var isVisible by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(false) }
    var isTitleVisible by remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color.White

    // Set the status bar color when the screen is launched
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false, // set to true if your text/icons are dark
        )

        isTitleVisible = true
        delay(100) // Small delay before animation starts
        isVisible = true
        delay(400)
        isButtonVisible = true
    }
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFAFAFA))
                .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        AnimatedVisibility(
            visible = isTitleVisible,
            enter =
                fadeIn(
                    animationSpec =
                        tween(
                            durationMillis = 500,
                            easing = FastOutSlowInEasing,
                        ),
                ) +
                    slideInVertically(
                        animationSpec =
                            tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing,
                            ),
                        initialOffsetY = { it / 2 },
                    ),
        ) {
            // MoveMate Logo and Title
            MoveMateHeader()
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter =
                scaleIn(
                    animationSpec =
                        tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing,
                        ),
                    initialScale = 0.8f,
                ) +
                    fadeIn(
                        animationSpec =
                            tween(
                                durationMillis = 800,
                                easing = FastOutSlowInEasing,
                            ),
                    ),
        ) {
            PackageIllustration()
        }

        // Estimation Content
        EstimationContent(
            estimatedAmount = estimatedAmount,
            currency = currency,
            isVisible = isVisible,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.height(100.dp)) {
            AnimatedVisibility(
                visible = isButtonVisible,
                enter =
                    fadeIn(
                        animationSpec =
                            tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing,
                            ),
                    ) +
                        slideInVertically(
                            animationSpec =
                                tween(
                                    durationMillis = 500,
                                    easing = FastOutSlowInEasing,
                                ),
                            initialOffsetY = { it / 2 },
                        ),
            ) {
                CustomButton(onClick = onBackToHome, text = "Back to Home")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun MoveMateHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "MoveMate",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = colorResource(R.color.app_color_purple),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.delivery_truck),
            contentDescription = "MoveMate Logo",
            tint = colorResource(R.color.app_color_orange),
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
private fun PackageIllustration() {
    // 3D-style package box using CSS-like styling with Compose
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier,
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
    currency: String,
    isVisible: Boolean = true,
) {
    // Extract numeric value from estimatedAmount (remove $ and convert to Int)
    val targetAmount =
        remember {
            estimatedAmount.replace("$", "").replace(",", "").toIntOrNull() ?: 1452
        }

    val startAmount = 1091
    var currentAmount by remember { mutableIntStateOf(startAmount) }
    var shouldStartCounting by remember { mutableStateOf(false) }

    // Start counting animation after the fade-in animation completes
    LaunchedEffect(shouldStartCounting) {
        if (shouldStartCounting) {
            val animationDuration = 2000 // 2 seconds
            val steps = 100 // Number of animation steps
            val stepDelay = animationDuration / steps
            val amountDifference = targetAmount - startAmount

            repeat(steps) { step ->
                val progress = (step + 1).toFloat() / steps
                // Use easing for smooth animation
                val easedProgress = FastOutSlowInEasing.transform(progress)
                currentAmount = startAmount + (amountDifference * easedProgress).toInt()
                delay(stepDelay.toLong())
            }
            // Ensure we end exactly at target
            currentAmount = targetAmount
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter =
            fadeIn(
                animationSpec =
                    tween(
                        durationMillis = 600,
                        delayMillis = 100, // Small delay after package animation
                    ),
            ) +
                slideInVertically(
                    animationSpec =
                        tween(
                            durationMillis = 600,
                            delayMillis = 100,
                        ),
                    initialOffsetY = { it / 4 }, // Slide in from 25% down
                ),
    ) {
        // Trigger counter animation when this content becomes visible
        LaunchedEffect(Unit) {
            delay(100) // Wait for fade-in animation to complete
            shouldStartCounting = true
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Total Estimated Amount",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF374151),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Animated counter display
                Text(
                    text = "$$currentAmount",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF49C38D), // Green color
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = currency,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF49C38D),
                    modifier =
                        Modifier
                            .align(Alignment.Bottom)
                            .padding(bottom = 2.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "This amount is estimated this will vary\nif you change your location or weight",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoveMateEstimationScreenPreview() {
    MaterialTheme {
        ResultsScreen()
    }
}
