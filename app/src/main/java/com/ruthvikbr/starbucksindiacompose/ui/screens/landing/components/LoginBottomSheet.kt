// 此代碼實現了Starbucks應用的登錄底部表單界面。
// 它提供了用戶名和密碼輸入，以及登錄、註冊和獲取幫助的功能。

package com.ruthvikbr.starbucksindiacompose.ui.screens.landing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ruthvikbr.starbucksindiacompose.R
import com.ruthvikbr.starbucksindiacompose.ui.components.LinkerText
import com.ruthvikbr.starbucksindiacompose.ui.components.SpacerComponent
import com.ruthvikbr.starbucksindiacompose.ui.components.StarbucksTextField
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryBlack
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryWhite

@Composable
fun LoginBottomSheet(
    onGetHelpClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    onLoginButtonClicked: (String, String) -> Unit,
) {
    // 使用remember來保存狀態，確保在重組時不會丟失
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.65f)
            .background(PrimaryWhite)
            .padding(24.dp),
    ) {
        // 登錄標題
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.h2,
            color = PrimaryBlack,
        )
        SpacerComponent(spaceInDp = 48.dp)

        // 用戶名輸入框
        StarbucksTextField(
            value = username,
            placeholder = stringResource(id = R.string.username_hint),
            onValueChange = { username = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            label = stringResource(id = R.string.username),
        )
        SpacerComponent(spaceInDp = 24.dp)

        // 密碼輸入框
        StarbucksTextField(
            value = password,
            placeholder = stringResource(id = R.string.password_hint),
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            visualTransformation = PasswordVisualTransformation(),
            label = stringResource(id = R.string.password),
        )
        SpacerComponent(spaceInDp = 12.dp)

        // "沒有帳號？註冊"鏈接
        LinkerText(
            primaryText = stringResource(id = R.string.dont_have_an_account),
            link = stringResource(id = R.string.sign_up),
            horizontalArrangement = Arrangement.Start,
            onLinkClicked = onSignUpClicked,
        )
        SpacerComponent(spaceInDp = 48.dp)

        // 登錄按鈕
        AuthButton(
            enabled = username.isNotEmpty() && password.isNotEmpty(),
            text = stringResource(id = R.string.login),
            onButtonClicked = { onLoginButtonClicked(username, password) }
        )
        SpacerComponent(spaceInDp = 8.dp)

        // "遇到問題？獲取幫助"鏈接
        LinkerText(
            primaryText = stringResource(id = R.string.facing_trouble),
            link = stringResource(id = R.string.get_help),
            horizontalArrangement = Arrangement.Center,
            onLinkClicked = onGetHelpClicked,
        )
    }
}