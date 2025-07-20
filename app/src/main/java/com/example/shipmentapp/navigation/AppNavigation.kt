package com.example.shipmentapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shipmentapp.presentation.calculate.CalculateScreen
import com.example.shipmentapp.presentation.home.HomeScreen
import com.example.shipmentapp.presentation.search.SearchScreen
import com.example.shipmentapp.presentation.shipmentHistory.ShipmentHistoryScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = AppRoute.MainWithBottomNav
    ) {
        composable<AppRoute.MainWithBottomNav> {
            MainScreenWithBottomNav(navController)
        }

        composable<AppRoute.SearchScreen> {
            SearchScreen()
        }

        composable<AppRoute.CalculateScreen> {
            CalculateScreen()
        }

        composable<AppRoute.ShipmentHistoryScreen> {
            ShipmentHistoryScreen()
        }
    }
}

@Composable
fun MainScreenWithBottomNav(navController: NavHostController) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = { BottomNavigationBar(selectedTabIndex) { selectedTabIndex = it } }
    ) { paddingValues ->
        // Your main content here based on selectedTabIndex
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (selectedTabIndex) {
                0 -> {
                    // Groups screen content
                    HomeScreen(
//                        modifier = Modifier.padding(paddingValues),
                        onSearchBarClick = {
                            navController.navigate(AppRoute.SearchScreen)
                        }
                    )
                }

                1 -> {
                   navController.navigate(AppRoute.CalculateScreen)
                }

                2 -> {
                    navController.navigate(AppRoute.ShipmentHistoryScreen)
                }

                3 -> {
                    Text(
                        "Profile Screen",
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

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