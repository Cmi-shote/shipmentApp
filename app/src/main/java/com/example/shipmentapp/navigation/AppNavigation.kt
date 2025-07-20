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
import com.example.shipmentapp.presentation.results.ResultsScreen
import com.example.shipmentapp.presentation.search.SearchScreen
import com.example.shipmentapp.presentation.shipmentHistory.ShipmentHistoryScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = AppRoute.MainWithBottomNav
    ) {
        composable<AppRoute.MainWithBottomNav> {
            HomeScreen(
                onSearchBarClick = {
                    navController.navigate(AppRoute.SearchScreen)
                },
                navigateToCalculateScreen = {
                    navController.navigate(AppRoute.CalculateScreen)
                },
                navigateToShipmentHistoryScreen = {
                    navController.navigate(AppRoute.ShipmentHistoryScreen)
                }
            )
        }

        composable<AppRoute.SearchScreen> {
            SearchScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<AppRoute.CalculateScreen> {
            CalculateScreen(
                onCalculateClick = {
                    navController.navigate(AppRoute.ResultsScreen)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<AppRoute.ShipmentHistoryScreen> {
            ShipmentHistoryScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<AppRoute.ResultsScreen> {
            ResultsScreen(
                onBackToHome = {
                    navController.navigate(AppRoute.ShipmentHistoryScreen){
                        popUpTo(AppRoute.MainWithBottomNav)
                        launchSingleTop = true
                    }
                }

            )
        }
    }
}