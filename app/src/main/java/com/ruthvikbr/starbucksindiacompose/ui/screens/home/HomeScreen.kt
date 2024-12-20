// 此程式碼定義了 HomeScreen，作為應用的主頁面。
// 它顯示了多個組件，包括標題、優惠輪播圖、熱門菜單項目、咖啡新聞與星巴克新聞。
// 此頁面結合 ViewModel 和 ComposeNavigator 實現數據驅動的互動與導航功能。

package com.ruthvikbr.starbucksindiacompose.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptionsBuilder
import com.ruthvikbr.starbucksindiacompose.ui.components.StarbucksColumn
import com.ruthvikbr.starbucksindiacompose.ui.screens.home.components.CoffeeNews
import com.ruthvikbr.starbucksindiacompose.ui.screens.home.components.OfferCarousel
import com.ruthvikbr.starbucksindiacompose.ui.screens.home.components.PopularMenuItems
import com.ruthvikbr.starbucksindiacompose.ui.screens.home.components.StarbucksNews
import com.ruthvikbr.starbucksindiacompose.ui.screens.home.components.Title
import com.starbuckscompose.navigation.ComposeNavigator
import com.starbuckscompose.navigation.StarbucksScreen
import java.net.URLEncoder

@Composable
fun HomeScreen(
    composeNavigator: ComposeNavigator, // 負責導航邏輯的 ComposeNavigator。
    viewModel: HomeViewModel = hiltViewModel(), // 使用 Hilt 提供的 HomeViewModel。
) {
    // 從 ViewModel 收集優惠輪播圖的數據。
    val carouselList by viewModel.carouselItemList.collectAsState()
    val carouselItems by carouselList.collectAsState(initial = emptyList())

    // 收集熱門菜單項目的數據。
    val popularItemsList by viewModel.popularMenuItemsList.collectAsState()
    val popularItems by popularItemsList.collectAsState(initial = emptyList())

    // 收集星巴克新聞的數據。
    val starbucksNewsList by viewModel.starbucksNewsItemsList.collectAsState()
    val starbucksNews by starbucksNewsList.collectAsState(initial = emptyList())

    // 使用 StarbucksColumn 組件組織頁面布局。
    StarbucksColumn {
        Title() // 顯示頁面標題。
        OfferCarousel(items = carouselItems) // 顯示優惠輪播圖。
        PopularMenuItems(onMenuItemClicked = { itemName ->
            // 點擊菜單項目後導航至訂購頁面。
            composeNavigator.navigate(StarbucksScreen.Order.route.plus("/$itemName"))
        }, popularMenuItems = popularItems)
        CoffeeNews { title, imageUrl, content ->
            // 點擊咖啡新聞後導航至新聞詳情頁面。
            composeNavigator.navigate(
                StarbucksScreen.NewsScreen.route
                    .plus("/$title/${URLEncoder.encode(imageUrl,"UTF-8")}/$content"),
            )
        }
        StarbucksNews(onNewsItemClicked = { title, imageUrl, content ->
            // 點擊星巴克新聞後導航至新聞詳情頁面。
            composeNavigator.navigate(
                StarbucksScreen.NewsScreen.route
                    .plus("/$title/${URLEncoder.encode(imageUrl,"UTF-8")}/$content"),
            )
        }, newsList = starbucksNews)
    }
}

/*
// 預覽 HomeScreen 組件，並模擬其導航功能。
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    // 模擬 ComposeNavigator 的導航邏輯。
    val fakeComposeNavigator = object : ComposeNavigator() {
        override fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
            println("導航至: $route") // 模擬導航操作。
        }

        override fun popUpTo(route: String, inclusive: Boolean) {
            println("返回至: $route, 是否包含當前路由: $inclusive") // 模擬返回操作。
        }
    }

    // 預覽 HomeScreen，使用模擬數據與導航。
    HomeScreen(composeNavigator = fakeComposeNavigator)
}*/
