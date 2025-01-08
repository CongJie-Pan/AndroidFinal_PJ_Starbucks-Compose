package com.ruthvikbr.starbucksindiacompose.ui.screens.orderprocessing.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ruthvikbr.domain.models.DMOrderItem
import com.ruthvikbr.domain.usecases.UpdateOrderItemAction
import com.ruthvikbr.starbucksindiacompose.R
import com.ruthvikbr.starbucksindiacompose.ui.components.SpacerComponent
import com.ruthvikbr.starbucksindiacompose.ui.screens.order.components.OrderItemCard
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryBlack

/*
 * 此檔案實現訂單摘要的UI組件
 * 負責顯示購物車中所有商品的列表
 * 提供訂單項目的數量修改功能
 */

@Composable
fun OrderSummary(
    cartItems: List<DMOrderItem>,        // 購物車商品列表
    onCartItemCountUpdate: (DMOrderItem, UpdateOrderItemAction) -> Unit    // 更新購物車商品數量的回調函數
) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        SpacerComponent(spaceInDp = 16.dp)
        Text(
            text = stringResource(id = R.string.order_summary_title),
            style = MaterialTheme.typography.h4,
            color = PrimaryBlack
        )
        SpacerComponent(spaceInDp = 16.dp)
        cartItems.forEach { item ->
            SpacerComponent(spaceInDp = 4.dp)
            OrderItemCard(dmOrderItem = item, onCartItemCountUpdate)
            SpacerComponent(spaceInDp = 4.dp)
        }
    }
}
