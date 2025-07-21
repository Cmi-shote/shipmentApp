package com.example.shipmentapp.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    data object MainWithBottomNav : AppRoute

    @Serializable
    data object SearchScreen : AppRoute

    @Serializable
    data object CalculateScreen : AppRoute

    @Serializable
    data object ShipmentHistoryScreen : AppRoute

    @Serializable
    data object ResultsScreen : AppRoute
}
