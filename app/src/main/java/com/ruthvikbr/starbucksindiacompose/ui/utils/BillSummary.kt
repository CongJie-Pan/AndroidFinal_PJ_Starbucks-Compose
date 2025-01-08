package com.ruthvikbr.starbucksindiacompose.ui.utils

/**
 * 此檔案用於處理星巴克應用程式的帳單摘要資訊
 * 包含購物車總額、稅額與最終總額的資料結構
 * 主要用於結帳流程中的金額計算與顯示
 */

data class BillSummary(
    // 購物車中所有商品的總金額
    var cartTotal: Int,
    
    // 需要支付的稅額
    var tax: Int,
    
    // 含稅的最終應付金額
    var grandTotal: Int
)
