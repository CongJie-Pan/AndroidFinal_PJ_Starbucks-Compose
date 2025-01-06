package com.ruthvikbr.starbucksindiacompose.ui.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ruthvikbr.starbucksindiacompose.ui.components.AppVersion
import com.ruthvikbr.starbucksindiacompose.ui.components.SpacerComponent
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components.Logout
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components.ProfileAppBar
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components.ProfileDetails
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components.ProfileMenuItem
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components.ReferralCard
import com.ruthvikbr.starbucksindiacompose.ui.theme.HouseGreenSecondary
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryWhite
import com.ruthvikbr.starbucksindiacompose.ui.utils.ProfileMenuItemsList
import com.starbuckscompose.navigation.ComposeNavigator
import com.starbuckscompose.navigation.StarbucksScreen
import java.util.*

/*
 * 此檔案實現了 Starbucks APP 的個人檔案頁面
 * 顯示用戶的個人資訊和各種設定選項
 * 包含導航功能和登出選項
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(composeNavigator: ComposeNavigator) {
    // Scaffold 用於實現基本的頁面結構
    // 頂部欄包含返回按鈕、通知和設定選項
    // LazyColumn 用於顯示個人資料和選單項目
    Scaffold(topBar = {
        ProfileAppBar(
            onBackButtonClicked = {
                composeNavigator.navigateUp()
            },
            onNotificationsButtonClicked = {},
            onSettingsButtonClicked = {
                composeNavigator.navigate(StarbucksScreen.Settings.route)
            },
        )
    }) { _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(HouseGreenSecondary),
        ) {
            item {
                ProfileDetails()
            }
            items(ProfileMenuItemsList.items) { menuItem ->
                ProfileMenuItem(menuItem = menuItem)
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().background(PrimaryWhite),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Logout(onLogoutButtonClicked = {})
                    SpacerComponent(spaceInDp = 64.dp)
                    ReferralCard()
                    SpacerComponent(spaceInDp = 32.dp)
                    AppVersion()
                    SpacerComponent(spaceInDp = 150.dp)
                }
            }
        }
    }
}
