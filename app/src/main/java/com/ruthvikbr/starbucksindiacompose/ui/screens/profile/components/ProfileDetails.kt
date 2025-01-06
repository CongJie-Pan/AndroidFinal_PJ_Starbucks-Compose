package com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components

import ProfilePicture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ruthvikbr.starbucksindiacompose.ui.components.SpacerComponent
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryWhite

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.ProfileViewModel

/*
 * 此檔案負責顯示用戶的詳細個人資料
 * 使用 ViewModel 來管理和獲取用戶數據
 * 展示用戶頭像、姓名、電子郵件等個人資訊
 */

@Composable
fun ProfileDetails(viewModel: ProfileViewModel = hiltViewModel()) {
    // 使用 collectAsState 來觀察用戶數據的變化
    // 顯示用戶的基本資料，包括姓名、歡迎訊息
    // 展示用戶的聯絡資訊和生日
    val user = viewModel.user.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ProfilePicture()
        SpacerComponent(spaceInDp = 16.dp)
        Text(
            text = "${user?.firstName} ${user?.lastName}",
            style = MaterialTheme.typography.h2,
            color = PrimaryWhite
        )
        SpacerComponent(spaceInDp = 4.dp)
        Text(
            text = "Welcome ${user?.firstName}",
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Normal),
            color = PrimaryWhite,
        )
        SpacerComponent(spaceInDp = 16.dp)
        Text(
            text = "Email: ${user?.email}",
            style = MaterialTheme.typography.body1,
            color = PrimaryWhite
        )
        Text(
            text = "Mobile: ${user?.mobileNumber}",
            style = MaterialTheme.typography.body1,
            color = PrimaryWhite
        )
        Text(
            text = "Birthday: ${user?.birthday}",
            style = MaterialTheme.typography.body1,
            color = PrimaryWhite
        )
    }
}
