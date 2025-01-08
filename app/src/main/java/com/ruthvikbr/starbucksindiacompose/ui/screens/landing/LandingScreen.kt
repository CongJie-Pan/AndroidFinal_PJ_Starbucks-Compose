// 此程式碼實現了Starbucks應用的登陸頁面。
// 它包含一個背景圖片、一個可點擊的底部按鈕，以及一個可滑動的底部表單，用於登錄和獲取幫助。

package com.ruthvikbr.starbucksindiacompose.ui.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ruthvikbr.starbucksindiacompose.R
import com.ruthvikbr.starbucksindiacompose.ui.screens.landing.components.GetHelpBottomSheet
import com.ruthvikbr.starbucksindiacompose.ui.screens.landing.components.LoginBottomSheet
import com.ruthvikbr.starbucksindiacompose.ui.theme.HouseGreenSecondary
import com.ruthvikbr.starbucksindiacompose.ui.theme.LightGreen
import com.ruthvikbr.starbucksindiacompose.ui.utils.LandingScreenBottomSheet
import com.starbuckscompose.navigation.ComposeNavigator
import com.starbuckscompose.navigation.StarbucksScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LandingScreen(composeNavigator: ComposeNavigator) {
    // 當前顯示的底部表單類型
    var currentBottomSheet: LandingScreenBottomSheet? by remember {
        mutableStateOf(LandingScreenBottomSheet.LoginSheet)
    }

    // 創建協程作用域和腳手架狀態
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    // 關閉底部表單的函數
    val closeSheet: () -> Unit = {
        scope.launch {
            scaffoldState.bottomSheetState.collapse()
        }
    }

    // 打開底部表單的函數
    val openSheet: (LandingScreenBottomSheet) -> Unit = {
        currentBottomSheet = it
        scope.launch { scaffoldState.bottomSheetState.expand() }
    }

    // 使用BottomSheetScaffold來創建可滑動的底部表單
    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            currentBottomSheet?.let { currentSheet ->
                SheetLayout(
                    currentSheet,
                    closeSheet,
                    onGetHelpClicked = { openSheet(LandingScreenBottomSheet.GetHelpSheet) },
                    onSignUpClicked = { composeNavigator.navigate(StarbucksScreen.SignUp.route) },
                    onLoginButtonClicked = { username: String, password: String ->
                        // 這裡應該處理登錄邏輯，現在只是簡單地導航到儀表板
                        composeNavigator.navigate(StarbucksScreen.Dashboard.name)
                    },
                )
            }
        },
    ) {
        // 主要內容：背景圖片和底部的圓形按鈕
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.BottomCenter,
        ) {
            // 背景圖片
            Image(
                painter = painterResource(id = R.drawable.landing),
                contentDescription = stringResource(id = R.string.landing_img),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            // 底部的圓形按鈕
            Box(
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(LightGreen)
                    .clickable {
                        openSheet(currentBottomSheet ?: LandingScreenBottomSheet.LoginSheet)
                    },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.start_login_btn),
                    modifier = Modifier.size(24.dp),
                    tint = HouseGreenSecondary,
                )
            }
        }
    }
}

// SheetLayout 函數用於根據當前選擇的底部表單類型顯示相應的內容
@Composable
fun SheetLayout(
    currentScreen: LandingScreenBottomSheet,
    onCloseBottomSheet: () -> Unit,
    onGetHelpClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    onLoginButtonClicked: (String, String) -> Unit
) {
    when (currentScreen) {
        LandingScreenBottomSheet.LoginSheet -> LoginBottomSheet(
            onGetHelpClicked = onGetHelpClicked,
            onSignUpClicked = onSignUpClicked,
            onLoginButtonClicked = onLoginButtonClicked
        )
        LandingScreenBottomSheet.GetHelpSheet -> GetHelpBottomSheet(onCloseBottomSheet)
    }
}