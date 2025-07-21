package com.example.shipmentapp.presentation.shipmentHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shipmentapp.models.Shipment
import com.example.shipmentapp.models.ShipmentStatus
import com.example.shipmentapp.models.sampleShipments // Assuming sampleShipments is your data source
import com.example.shipmentapp.models.tabs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Data class to hold the UI state for ShipmentHistoryScreen
data class ShipmentHistoryUiState(
    val shipments: List<Shipment> = sampleShipments, // All shipments, or initial set
    val filteredShipments: List<Shipment> = sampleShipments, // Filtered based on tab
    val selectedTabIndex: Int = 0,
)

class ShipmentHistoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ShipmentHistoryUiState())
    val uiState: StateFlow<ShipmentHistoryUiState> = _uiState.asStateFlow()

    // Initialize filtered shipments based on the initial selected tab (e.g., "All" or first tab)
    init {
        filterShipmentsByTab(0) // Filter for the initial tab (index 0)
    }

    fun onTabSelected(index: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(selectedTabIndex = index)
            }
            filterShipmentsByTab(index)
        }
    }

    private fun filterShipmentsByTab(tabIndex: Int) {
        val selectedTab = tabs[tabIndex]
        val filteredList =
            when (selectedTab.status) {
                null -> _uiState.value.shipments // "All" tab or similar
                else ->
                    if (selectedTab.status == ShipmentStatus.ALL) {
                        uiState.value.shipments
                    } else {
                        uiState.value.shipments.filter {
                            it.status == selectedTab.status
                        }
                    }
            }
        _uiState.update { currentState ->
            currentState.copy(filteredShipments = emptyList())
        }
        viewModelScope.launch {
            delay(200)
            _uiState.update { currentState ->
                currentState.copy(filteredShipments = filteredList)
            }
        }
    }
}
