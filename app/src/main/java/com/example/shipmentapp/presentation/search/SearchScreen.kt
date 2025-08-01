package com.example.shipmentapp.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shipmentapp.R
import com.example.shipmentapp.presentation.components.SearchBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    viewModel: SearchViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var isCardVisible by remember { mutableStateOf(false) }
    var isTopSectionVisible by remember { mutableStateOf(false) }

    // Trigger the animation when the composable is first composed
    LaunchedEffect(Unit) {
        delay(100) // Small initial delay
        isTopSectionVisible = true
        delay(300) // a small delay before showing the card
        isCardVisible = true
    }

    LaunchedEffect(uiState.searchQuery) {
        isCardVisible = false // Start fade out
        delay(500)
        isCardVisible = true // Start fade/slide in
    }

    LazyColumn(modifier = modifier) {
        item {
            TopAppBar(
                title = {
                    SearchBar(
                        searchQuery = uiState.searchQuery,
                        showBackIcon = true,
                        onSearchQueryChange = viewModel::onSearchQueryChange,
                        onSearchBarClick = { },
                        onBackClick = {
                            viewModel.clearSearch()
                            onBackClick()
                        },
                        modifier =
                            Modifier
                                .padding(end = 16.dp)
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = "searchK"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 1000)
                                    },
                                ),
                        placeholder = "#NEJ200899",
                    )
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(id = R.color.app_color_purple),
                    ),
            )
        }

        item {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(colorResource(id = R.color.app_color_purple)),
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            // Animated Card with fade-in effect
            AnimatedVisibility(
                visible = isCardVisible,
                enter =
                    fadeIn(
                        animationSpec =
                            tween(
                                durationMillis = 600,
                                delayMillis = 0,
                            ),
                    ) +
                        slideInVertically(
                            animationSpec =
                                tween(
                                    durationMillis = 600,
                                    delayMillis = 0,
                                ),
                            initialOffsetY = { it / 4 }, // Slide in from 25% down
                        ),
                exit =
                    fadeOut(
                        animationSpec = tween(durationMillis = 100),
                    ),
            ) {
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Column {
                        uiState.filteredOrders.forEachIndexed { index, order ->
                            if (index != 0) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 24.dp),
                                    color = Color.LightGray.copy(alpha = 0.5f),
                                )
                            }
                            ShipmentInfoCard(
                                order = order,
                            )
                        }
                    }
                }
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun PreviewSearchScreen(modifier: Modifier = Modifier) {
//    ShipmentAppTheme {
//        SearchScreen()
//    }
// }
