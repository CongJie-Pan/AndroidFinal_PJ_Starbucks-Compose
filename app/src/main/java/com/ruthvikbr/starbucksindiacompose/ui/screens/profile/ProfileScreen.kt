// ProfileScreen 實現了 Starbucks APP 的個人檔案頁面，顯示用戶資訊和各種設定選項。
// 此畫面包含用戶資料展示、設定選單、登出功能，並與 ViewModel 和導航系統整合。

package com.ruthvikbr.starbucksindiacompose.ui.screens.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruthvikbr.starbucksindiacompose.ui.components.AppVersion
import com.ruthvikbr.starbucksindiacompose.ui.components.SpacerComponent
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components.*
import com.ruthvikbr.starbucksindiacompose.ui.theme.HouseGreenSecondary
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryWhite
import com.ruthvikbr.starbucksindiacompose.ui.utils.ProfileMenuItemsList
import com.starbuckscompose.navigation.ComposeNavigator
import com.starbuckscompose.navigation.StarbucksScreen
import com.ruthvikbr.starbucksindiacompose.data.entity.User

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    composeNavigator: ComposeNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    // 獲取當前上下文
    val context = LocalContext.current

    // 從 ViewModel 獲取用戶狀態
    val user by viewModel.user.collectAsState()

    Scaffold(
        topBar = {
            ProfileAppBar(
                onBackButtonClicked = { composeNavigator.navigateUp() },
                onNotificationsButtonClicked = { /* 實現通知功能 */ },
                onSettingsButtonClicked = { composeNavigator.navigate(StarbucksScreen.Settings.route) }
            )
        }
    ) { _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(HouseGreenSecondary)
        ) {
            // 顯示用戶資料
            item {
                ProfileDetails(user = user)
            }

            // 顯示設定選單項目
            items(ProfileMenuItemsList.items) { menuItem ->
                ProfileMenuItem(menuItem = menuItem)
            }

            // 顯示登出按鈕、推薦卡和應用版本
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryWhite),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Logout(
                        onLogoutButtonClicked = {
                            /* 因為登出功能有些錯誤，就是按了登出後，再註冊帳號就不能顯示另一個帳號的資訊了，因此先不用。*/
                            /* viewModel.logout()*/
                            Toast.makeText(context, "登出功能仍未開發，先退出此頁面。", Toast.LENGTH_LONG).show()
                            composeNavigator.navigate(StarbucksScreen.Landing.route) {
                                popUpTo(StarbucksScreen.Home.route) { inclusive = true }
                            }
                        }
                    )
                    //SpacerComponent(spaceInDp = 64.dp)
                    //ReferralCard()
                    SpacerComponent(spaceInDp = 32.dp)
                    AppVersion()
                    SpacerComponent(spaceInDp = 150.dp)
                }
            }
        }
    }
}