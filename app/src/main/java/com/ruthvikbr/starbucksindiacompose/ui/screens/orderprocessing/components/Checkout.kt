package com.ruthvikbr.starbucksindiacompose.ui.screens.orderprocessing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ruthvikbr.domain.models.DMOrderItem
import com.ruthvikbr.domain.usecases.UpdateOrderItemAction
import com.ruthvikbr.starbucksindiacompose.R
import com.ruthvikbr.starbucksindiacompose.ui.theme.SecondaryWhite
import com.ruthvikbr.starbucksindiacompose.ui.utils.BillSummary

/*
 * 此檔案實現了星巴克應用的結帳畫面UI組件
 * 負責顯示訂單摘要、帳單明細和支付選項
 * 使用Jetpack Compose實現可滾動的結帳介面
 */

@Composable
fun Checkout(
    // billSummary: 包含訂單總金額等帳單資訊
    billSummary: BillSummary,
    // cartItems: 購物車中的商品列表
    cartItems: List<DMOrderItem>,
    // onBackPressed: 返回按鈕點擊事件處理
    onBackPressed: () -> Unit,
    // onCartItemCountUpdate: 更新購物車商品數量的處理函數
    onCartItemCountUpdate: (DMOrderItem, UpdateOrderItemAction) -> Unit,
    // onPaymentStatusUpdate: 更新支付方式的處理函數
    onPaymentStatusUpdate: (selectedPaymentMode: String) -> Unit,
    // selectedPaymentMode: 當前選擇的支付方式
    selectedPaymentMode: String
) {
    // Column佈局包含整個結帳畫面的內容
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(SecondaryWhite)
    ) {
        // 頂部導航欄
        StarbucksAppBar(title = stringResource(id = R.string.checkout), onBackPressed = {
            onBackPressed()
        })
        // 訂單商品摘要
        OrderSummary(cartItems = cartItems, onCartItemCountUpdate = onCartItemCountUpdate)
        // 帳單金額明細
        OrderBillSummary(billSummary = billSummary)
        // 支付方式選擇
        PaymentOptions(onPaymentStatusUpdate = onPaymentStatusUpdate, selectedPaymentMode)
    }
}
