package com.ruthvikbr.starbucksindiacompose.ui.utils

import com.ruthvikbr.starbucksindiacompose.R

sealed class OrderPaymentOptions(val label: String, val imageId: Int) {

    // 在台灣沒有人使用這個支付系統，所以棄之。
    /*object PhonePe : OrderPaymentOptions(label = "PhonePe", imageId = R.drawable.phonepe)*/
    object GooglePay : OrderPaymentOptions("Google Pay", R.drawable.gpay)
    object Store : OrderPaymentOptions("Pay at the Store", R.drawable.logo)
}
