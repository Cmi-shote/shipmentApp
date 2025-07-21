package com.example.shipmentapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Flip
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Enter the receipt number ...",
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    showBackIcon: Boolean = false,
    onActionClick: () -> Unit = {},
    onSearchBarClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    readOnly: Boolean = false,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        if (showBackIcon) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color.White,
                modifier =
                    Modifier.size(24.dp).clickable {
                        onBackClick()
                    },
            )
            Spacer(modifier = Modifier.width(12.dp))
        }

        Card(
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable(enabled = readOnly) { onSearchBarClick() },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color(0xFF8A8A8A),
                    modifier = Modifier.size(24.dp),
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    // Conditionally render Text vs Field
                    if (readOnly) {
                        Text(
                            text = if (searchQuery.isEmpty()) placeholder else searchQuery,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (searchQuery.isEmpty()) Color.Gray else Color.Black,
                        )
                    } else {
                        CustomOutlineField(
                            value = searchQuery,
                            onValueChange = onSearchQueryChange,
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = false,
                            placeholder = {
                                Text(
                                    text = placeholder,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFFF9800), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    IconButton(
                        onClick = onActionClick,
                        modifier = Modifier.size(40.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Flip,
                            contentDescription = "Action",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp).rotate(90f),
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun SearchBarPreview() {
    ShipmentAppTheme {
        SearchBar()
    }
}
