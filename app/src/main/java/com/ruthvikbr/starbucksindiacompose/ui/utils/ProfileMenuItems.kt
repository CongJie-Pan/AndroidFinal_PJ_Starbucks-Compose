package com.ruthvikbr.starbucksindiacompose.ui.utils

import com.ruthvikbr.starbucksindiacompose.R

data class ProfileMenuItem(
    val label: String,
    val icon: Int,
)

object ProfileMenuItemsList {
    val items = listOf(
        //ProfileMenuItem("星禮程Starbucks® Rewards", R.drawable.rewards),
        ProfileMenuItem("訂單", R.drawable.coffee),
        //ProfileMenuItem("星巴克支付", R.drawable.credit),
        //ProfileMenuItem("其他付款方式", R.drawable.other_payment),
        //ProfileMenuItem("幫助中心", R.drawable.help_center),
        //ProfileMenuItem("已保存的文章與新聞", R.drawable.saved),
        //ProfileMenuItem("在 Play 商店為我們評分", R.drawable.playstore),
    )
}
