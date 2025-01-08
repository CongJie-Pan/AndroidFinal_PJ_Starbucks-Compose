package com.ruthvikbr.starbucksindiacompose.ui.screens.orderprocessing

import androidx.compose.foundation.background
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruthvikbr.starbucksindiacompose.R
import com.ruthvikbr.starbucksindiacompose.ui.components.BottomSheetComposable
import com.ruthvikbr.starbucksindiacompose.ui.screens.orderprocessing.components.Checkout
import com.ruthvikbr.starbucksindiacompose.ui.screens.orderprocessing.components.EmptyCheckoutScreen
import com.ruthvikbr.starbucksindiacompose.ui.theme.SecondaryWhite
import com.starbuckscompose.navigation.ComposeNavigator
import com.starbuckscompose.navigation.StarbucksScreen
import kotlinx.coroutines.launch

/*
 * 此檔案實現了結帳畫面的完整邏輯和UI整合
 * 管理結帳流程中的狀態和用戶交互
 * 整合了底部彈出支付表單的功能
 */

@ExperimentalMaterialApi
@Composable
fun CheckoutScreen(
    // composeNavigator: 處理畫面導航的導航器
    composeNavigator: ComposeNavigator,
    // viewModel: 管理結帳相關的數據和業務邏輯
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    // 監聽購物車商品狀態
    val cartItemsState by viewModel.cartItems.collectAsState()
    val cartItems by cartItemsState.collectAsState(initial = emptyList())

    // 協程作用域，用於處理異步操作
    val coroutineScope = rememberCoroutineScope()

    // 底部彈出表單的狀態管理
    val sheetState =
        rememberBottomSheetState(
            initialValue = if (cartItems.isEmpty()) BottomSheetValue.Collapsed else BottomSheetValue.Expanded
        )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    var selectedPaymentMode by remember {
        mutableStateOf("")
    }

    // 監聽購物車變化，自動展開或收起底部表單
    LaunchedEffect(key1 = cartItems) {
        if (cartItems.isEmpty()) {
            coroutineScope.launch {
                sheetState.collapse()
            }
        } else {
            coroutineScope.launch {
                sheetState.expand()
            }
        }
    }

    // 底部彈出式表單的架構設置
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetComposable(
                primaryText = stringResource(
                    R.string.order_total_text,
                    viewModel.calculateOrderTotal(cartItems).grandTotal
                ),
                buttonText = stringResource(id = R.string.order_payment_btn_text),
                buttonEnabled = selectedPaymentMode.isNotEmpty(),
                onCheckoutClicked = { composeNavigator.navigate(StarbucksScreen.OrderProcessing.route) }
            )
        },
        sheetPeekHeight = 0.dp,
        modifier = Modifier.background(SecondaryWhite)
    ) {
        // 根據購物車是否為空顯示不同的內容
        if (cartItems.isEmpty()) {
            EmptyCheckoutScreen {
                composeNavigator.navigateUp()
            }
        } else {
            Checkout(
                billSummary = viewModel.calculateOrderTotal(cartItems),
                cartItems = cartItems,
                onBackPressed = {
                    composeNavigator.navigateUp()
                },
                onCartItemCountUpdate = { item, action ->
                    coroutineScope.launch {
                        viewModel.updateOrderItem(item, action)
                    }
                },
                onPaymentStatusUpdate = { paymentMode ->
                    selectedPaymentMode = paymentMode
                },
                selectedPaymentMode
            )
        }
    }
}
