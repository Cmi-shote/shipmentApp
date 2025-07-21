package com.example.shipmentapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shipmentapp.R
import com.example.shipmentapp.models.User
import com.example.shipmentapp.models.sampleUser
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun HomePageToolbar(
    modifier: Modifier = Modifier,
    user: User = sampleUser,
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = user.avatar),
                    contentDescription = "Profile Picture",
                    modifier =
                        Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Row {
                        Icon(
                            Icons.Default.NearMe,
                            contentDescription = "Settings",
                            modifier = Modifier.size(16.dp),
                            tint = Color.White.copy(alpha = 0.7f),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Your location",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f),
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        user.location,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = {},
                modifier =
                    Modifier
                        .size(40.dp)
                        .background(Color.White, CircleShape),
            ) {
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = "Settings",
                    modifier = Modifier.size(24.dp),
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.app_color_purple),
            ),
        modifier = modifier,
    )
}

@Preview
@Composable
fun HomePageToolBarPreview(modifier: Modifier = Modifier) {
    ShipmentAppTheme {
        HomePageToolbar()
    }
}
