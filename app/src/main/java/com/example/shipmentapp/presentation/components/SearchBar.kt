package com.example.shipmentapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flip
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Enter the receipt number ...",
    onSearchQueryChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White, RoundedCornerShape(28.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Icon
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color(0xFF8A8A8A),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Search TextField
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                    onSearchQueryChange(newValue)
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Placeholder text
            if (searchQuery.isEmpty()) {
                Text(
                    text = placeholder,
                    color = Color(0xFF8A8A8A),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Action Button
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFFF9800), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onActionClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Flip,
                    contentDescription = "Action",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

// Alternative version using OutlinedTextField for better UX
@Composable
fun SearchBarAlternative(
    modifier: Modifier = Modifier,
    placeholder: String = "Enter the receipt number ...",
    onSearchQueryChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.background(color = Color(0xFF543B9C)).padding(vertical = 4.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, RoundedCornerShape(28.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Icon
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF8A8A8A),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Search TextField
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                    onSearchQueryChange(newValue)
                },
                placeholder = {
                    Text(
                        text = placeholder,
                        color = Color(0xFF8A8A8A),
                        fontSize = 16.sp
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Action Button
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFFF9800), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = onActionClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Flip,
                        contentDescription = "Action",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchBarPreview() {
    ShipmentAppTheme {
        SearchBar(
            onSearchQueryChange = { query ->
                // Handle search query change
            },
            onActionClick = {
                // Handle action button click
            }
        )
    }
}