package com.ruthvikbr.starbucksindiacompose.ui.utils

import com.ruthvikbr.starbucksindiacompose.R
import com.starbuckscompose.navigation.StarbucksScreen

data class NavigationItem(
    val label: String,
    val icon: Int,
    val route: String,
)

object BottomNavigationItems {
    val items = listOf(
        NavigationItem("主頁", R.drawable.ic_home, StarbucksScreen.Home.route),
        NavigationItem("我要訂餐", R.drawable.ic_coffee, StarbucksScreen.Order.name),
        NavigationItem("個人資料", R.drawable.ic_person, StarbucksScreen.Profile.route),
        // 因為專案業務需求，暫時移除店家頁面
        //NavigationItem("店家", R.drawable.ic_store, StarbucksScreen.Stores.route),
    )
}
