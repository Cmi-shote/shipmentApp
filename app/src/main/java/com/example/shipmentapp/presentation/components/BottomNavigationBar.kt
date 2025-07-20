package com.example.shipmentapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val navigationItems = listOf(
        BottomNavItem(title = "Home", icon = Icons.Outlined.Home, index = 0),
        BottomNavItem(title = "Calculate", icon = Icons.Outlined.Calculate, index = 1),
        BottomNavItem(title = "Shipment", icon = Icons.Default.History, index = 2),
        BottomNavItem(title = "Profile", icon = Icons.Outlined.Person, index = 3)
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        navigationItems.forEach { item ->

            NavigationBarItem(
                selected = selectedTabIndex == item.index,
                onClick = {
                    onTabSelected(item.index)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(item.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

// Data class for bottom navigation items
data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val index: Int
)