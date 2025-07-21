package com.example.shipmentapp.presentation.calculate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Unarchive
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.R
import com.example.shipmentapp.presentation.components.CustomButton
import com.example.shipmentapp.presentation.components.CustomOutlineField
import com.example.shipmentapp.presentation.components.CustomToolbar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CalculateScreen(
//    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onCalculateClick: () -> Unit = {}
) {
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var isCardVisible by remember { mutableStateOf(false) }

//    val systemUiController = rememberSystemUiController()
//    val statusBarColor = colorResource(id = R.color.app_color_purple)

    // Set the status bar color when the screen is launched
    LaunchedEffect(Unit) {
//        systemUiController.setStatusBarColor(
//            color = statusBarColor,
//            darkIcons = false // set to true if your text/icons are dark
//        )
        delay(100)
        isCardVisible = true
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomToolbar(
                title = "Calculate",
                onBackClick = onBackClick,
                modifier = Modifier
//                    .sharedElement(
//                    sharedContentState = rememberSharedContentState(key = "toolbar"),
//                    animatedVisibilityScope = animatedVisibilityScope,
//                    boundsTransform = { _, _ ->
//                        tween(durationMillis = 1000)
//                    }
//                )
            )
        },
        content = { paddingValues ->

            AnimatedVisibility(
                visible = isCardVisible,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 600,
                        delayMillis = 200 // Fixed delay instead of using undefined index
                    )
                ) + slideInVertically(
                    animationSpec = tween(
                        durationMillis = 300,
                        delayMillis = 200
                    ),
                    initialOffsetY = { it / 4 } // Slide in from 25% down
                )
            ) {
                // Content
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        DestinationSection()
                    }

                    item {
                        PackagingSection()
                    }

                    item {
                        CategoriesSection(
                            selectedCategories = selectedCategories,
                            onCategoriesChanged = { selectedCategories = it }
                        )
                    }

                    item {
                        CustomButton(
                            onClick = onCalculateClick,
                            modifier = Modifier.padding(vertical = 32.dp),
                            text = "Calculate"
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun DestinationSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Destination",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                DestinationField(
                    icon = Icons.Outlined.Unarchive,
                    label = "Sender location"
                )
                Spacer(modifier = Modifier.height(16.dp))

                DestinationField(
                    icon = Icons.Outlined.Archive,
                    label = "Receiver location"
                )

                Spacer(modifier = Modifier.height(16.dp))

                DestinationField(
                    icon = Icons.Filled.Scale,
                    label = "Approx weight"
                )
            }
        }
    }
}

@Composable
fun DestinationField(
    icon: ImageVector,
    label: String
) {
    var textValue by remember { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        CustomOutlineField(
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Row {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFF8A8A8A),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    VerticalDivider(
                        color = Color.Gray.copy(alpha = 0.2f),
                        modifier = Modifier.height(24.dp)
                    )
                }
            },
            placeholder = { Text(label) }
        )
    }
}

@Composable
fun PackagingSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Packaging",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = "What are you sending?",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF8A8A8A)
        )

        Spacer(modifier = Modifier.height(4.dp))

        PackagingDropdown()
    }
}

@Composable
fun PackagingDropdown() {
    CustomOutlineField(
        value = "Box",
        onValueChange = {},
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        leadingIcon = {
            Row {
                Image(
                    painter = painterResource(R.drawable.box1),
                    contentDescription = "Package",
                    modifier = Modifier.size(45.dp)
                )
                VerticalDivider(
                    color = Color.Gray.copy(alpha = 0.2f),
                    modifier = Modifier.height(45.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color(0xFF8A8A8A),
                modifier = Modifier.size(24.dp)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

@Composable
fun CategoriesSection(
    selectedCategories: Set<String>,
    onCategoriesChanged: (Set<String>) -> Unit
) {
    val categories = listOf(
        "Documents", "Glass", "Liquid", "Food",
        "Electronic", "Product", "Others"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Categories",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "What are you sending?",
            fontSize = 16.sp,
            color = Color(0xFF8A8A8A)
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = 4
    ) {
        categories.forEach { category ->
            CategoryChip(
                text = category,
                isSelected = category in selectedCategories,
                onClick = {
                    val newSelection = if (category in selectedCategories) {
                        selectedCategories - category
                    } else {
                        selectedCategories + category
                    }
                    onCategoriesChanged(newSelection)
                }
            )
        }
    }
}

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0xFF142333) else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = if (isSelected) Color(0xFF142333) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = text,
                fontSize = 14.sp,
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculateScreenPreview() {
    CalculateScreen()
}