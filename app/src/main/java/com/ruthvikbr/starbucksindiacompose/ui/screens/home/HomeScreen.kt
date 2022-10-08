package com.ruthvikbr.starbucksindiacompose.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruthvikbr.starbucksindiacompose.ui.components.StarbucksColumn
import com.ruthvikbr.starbucksindiacompose.ui.screens.home.components.OfferCarousel
import com.ruthvikbr.starbucksindiacompose.ui.screens.home.components.Title
import com.starbuckscompose.navigation.ComposeNavigator

@Composable
fun HomeScreen(
    composeNavigator: ComposeNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val carouselList by viewModel.carouselItemList.collectAsState()
    val carouselItems by carouselList.collectAsState(initial = emptyList())

    StarbucksColumn {
        Title()
        OfferCarousel(items = carouselItems)
    }
}