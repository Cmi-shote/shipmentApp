package com.example.shipmentapp.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shipmentapp.presentation.calculate.CalculateScreen
import com.example.shipmentapp.presentation.home.HomeScreen
import com.example.shipmentapp.presentation.results.ResultsScreen
import com.example.shipmentapp.presentation.search.SearchScreen
import com.example.shipmentapp.presentation.shipmentHistory.ShipmentHistoryScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(navController: NavHostController, animatedVisibilityScope: SharedTransitionScope) {
    NavHost(
        navController,
        startDestination = AppRoute.MainWithBottomNav
    ) {
        composable<AppRoute.MainWithBottomNav> {
            with(animatedVisibilityScope) {
                HomeScreen(
                    onSearchBarClick = {
                        navController.navigate(AppRoute.SearchScreen)
                    },
                    navigateToCalculateScreen = {
                        navController.navigate(AppRoute.CalculateScreen)
                    },
                    navigateToShipmentHistoryScreen = {
                        navController.navigate(AppRoute.ShipmentHistoryScreen)
                    },
                    animatedVisibilityScope = this@composable
                )
            }
        }

        composable<AppRoute.SearchScreen> {
            with(animatedVisibilityScope) {
                SearchScreen(
                    onBackClick = { navController.popBackStack() },
                    animatedVisibilityScope = this@composable
                )
            }
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