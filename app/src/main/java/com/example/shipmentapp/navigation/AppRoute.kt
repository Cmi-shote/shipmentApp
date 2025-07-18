package com.example.shipmentapp.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    data object MainWithBottomNav : AppRoute
}