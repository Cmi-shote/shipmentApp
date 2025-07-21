package com.example.shipmentapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shipmentapp.models.Order
import com.example.shipmentapp.models.sampleOrders
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchUiState(
    val searchQuery: String = "",
    val filteredOrders: List<Order> = sampleOrders,
)

class SearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    // All available orders
    private val allOrders = sampleOrders

    fun onSearchQueryChange(query: String) {
        val filteredResults =
            if (query.isBlank()) {
                allOrders // Show all orders when search is empty
            } else {
                allOrders.filter { order ->
                    order.orderNumber.lowercase().contains(query.lowercase())
                }
            }

        _uiState.update { it.copy(searchQuery = query, filteredOrders = emptyList()) }
        viewModelScope.launch {
            delay(200)
            _uiState.update { it.copy(filteredOrders = filteredResults) }
        }
    }

    fun clearSearch() {
        _uiState.value =
            _uiState.value.copy(
                searchQuery = "",
                filteredOrders = allOrders,
            )
    }
}
