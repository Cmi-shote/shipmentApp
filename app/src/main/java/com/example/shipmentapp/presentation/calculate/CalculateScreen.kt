package com.example.shipmentapp.presentation.calculate

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
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Unarchive
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.presentation.components.CustomOutlineField

@Composable
fun CalculateScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onCalculateClick: () -> Unit = {}
) {
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Header
        CalculateHeader(onBackClick = onBackClick)

        // Content
        LazyColumn(
            modifier = Modifier
                .weight(1f)
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
        }

        // Calculate Button
        CalculateButton(
            onClick = onCalculateClick,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        )
    }
}

@Composable
fun CalculateHeader(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF543B9C))
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Calculate",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 48.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DestinationSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Destination",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = Color.Black
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
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = Color.Black
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
                Icon(
                    imageVector = Icons.Default.GifBox,
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

@OptIn(ExperimentalLayoutApi::class)
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
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Categories",
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
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
                color = if (isSelected) Color(0xFF543B9C) else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = if (isSelected) Color(0xFF543B9C).copy(alpha = 0.1f) else Color.Transparent
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (isSelected) Color(0xFF543B9C) else Color(0xFF8A8A8A),
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Composable
fun CalculateButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF8C00)
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        Text(
            text = "Calculate",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculateScreenPreview() {
    CalculateScreen()
}