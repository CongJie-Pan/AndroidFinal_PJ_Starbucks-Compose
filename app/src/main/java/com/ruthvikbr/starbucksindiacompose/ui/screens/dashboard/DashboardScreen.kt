// 此程式碼定義了 DashboardScreen，作為應用的主要導航頁面。
// 它使用 Scaffold 和 BottomNavigationBar 組件，提供底部導航列功能。
// 此外，通過 NavHost 管理多頁面的導航邏輯，支持動態組件的顯示與切換。

package com.ruthvikbr.starbucksindiacompose.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ruthvikbr.starbucksindiacompose.ui.bottomBarRoute
import com.ruthvikbr.starbucksindiacompose.ui.components.BottomNavigationBar
import com.ruthvikbr.starbucksindiacompose.ui.utils.BottomNavigationItems
import com.starbuckscompose.navigation.ComposeNavigator
import com.starbuckscompose.navigation.StarbucksRoute
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen(composeNavigator: ComposeNavigator) {
    // 創建一個 NavController，用於管理頁面之間的導航。
    val navController = rememberNavController()

    // 使用 Scaffold 組件構建頁面骨架，包含底部導航列和內容區域。
    Scaffold(bottomBar = {
        // BottomNavigationBar 是自定義組件，用於顯示底部導航列。
        BottomNavigationBar(items = BottomNavigationItems.items, navController)
    }) {
        // 使用 NavHost 定義導航主機，管理多個頁面的導航路由。
        NavHost(
            navController = navController, // 傳入 NavController 用於導航管理。
            startDestination = StarbucksRoute.DashboardBottomNavRoute.name, // 設定導航的起始頁面。
            Modifier.padding(it), // 為內容區域設置內邊距，避免被底部導航列遮擋。
        ) {
            // bottomBarRoute 是自定義方法，用於配置底部導航的路由。
            bottomBarRoute(composeNavigator)
        }
    }
}


// 預覽 DashboardScreen 組件，並允許互動以模擬頁面編輯。
@Preview(showSystemUi = true)
@Composable
fun DecoratedComposablePreview() {
    // 模擬 ComposeNavigator 的導航行為
    val fakeComposeNavigator = object : ComposeNavigator() {
        override fun navigate(route: String, optionsBuilder: (NavOptionsBuilder.() -> Unit)?) {
            // 模擬導航操作，打印導航目標和參數。
            println("導航至: $route")
        }

        override fun popUpTo(route: String, inclusive: Boolean) {
            // 模擬返回操作，打印 popUpTo 的行為。
            println("返回至: $route, 是否包含當前路由: $inclusive")
        }
    }

    // 使用 Box 包裹 Text，允許點擊事件進行模擬編輯操作。
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .clickable { println("模擬編輯點擊事件") }
    ) {
        DashboardScreen(composeNavigator = fakeComposeNavigator)
    }
}
