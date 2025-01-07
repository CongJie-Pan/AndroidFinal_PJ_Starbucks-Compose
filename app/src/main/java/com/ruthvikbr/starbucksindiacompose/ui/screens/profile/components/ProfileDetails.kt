/*
 * 此組件負責顯示用戶的詳細個人資料，包括頭像、姓名、聯絡資訊等。
 * 它使用 ViewModel 來管理和獲取用戶數據，並以響應式方式更新 UI。
 */

package com.ruthvikbr.starbucksindiacompose.ui.screens.profile.components

import ProfilePicture
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.ui.components.SpacerComponent
import com.ruthvikbr.starbucksindiacompose.ui.screens.profile.ProfileViewModel
import com.ruthvikbr.starbucksindiacompose.ui.theme.PrimaryWhite

@Composable
fun ProfileDetails(viewModel: ProfileViewModel = hiltViewModel(),user: User?) {
    // 使用 collectAsState 來觀察用戶數據的變化
    val user = viewModel.user.collectAsState().value

    Log.d("ProfileDetails", "Collected user: $user")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // 顯示用戶頭像
        ProfilePicture()

        SpacerComponent(spaceInDp = 16.dp)

        // 顯示用戶全名
        Text(
            text = "${user?.firstName} ${user?.lastName}",
            style = MaterialTheme.typography.h4,
            color = PrimaryWhite,
            fontWeight = FontWeight.Bold
        )

        SpacerComponent(spaceInDp = 4.dp)

        // 顯示歡迎訊息
        Text(
            text = "Welcome ${user?.firstName}",
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal),
            color = PrimaryWhite
        )

        SpacerComponent(spaceInDp = 16.dp)

        // 顯示用戶聯絡資訊
        UserInfoItem(label = "Email", value = user?.email)
        UserInfoItem(label = "Mobile", value = user?.mobileNumber)
        UserInfoItem(label = "Birthday", value = user?.birthday)

        // 顯示用戶偏好設定
        SpacerComponent(spaceInDp = 8.dp)
        Text(
            text = "Preferences",
            style = MaterialTheme.typography.h6,
            color = PrimaryWhite,
            fontWeight = FontWeight.Bold
        )
        UserPreferenceItem(label = "SMS Notifications", isEnabled = user?.isSmsEnabled ?: false)
        UserPreferenceItem(label = "Email Notifications", isEnabled = user?.isEmailEnabled ?: false)
    }
}

// 定義了一個名為UserInfoItem的Composable函數,用於顯示用戶的資訊項目。
@Composable
private fun UserInfoItem(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.body1,
            color = PrimaryWhite.copy(alpha = 0.7f)
        )
        Text(
            text = value ?: "Not provided",
            style = MaterialTheme.typography.body1,
            color = PrimaryWhite
        )
    }
}

// 此 UserPreferenceItem Composable函數用於顯示用戶偏好設定項目,並根據啟用狀態顯示相應的文本和顏色。
@Composable
private fun UserPreferenceItem(label: String, isEnabled: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = PrimaryWhite.copy(alpha = 0.7f)
        )
        Text(
            text = if (isEnabled) "Enabled" else "Disabled",
            style = MaterialTheme.typography.body1,
            color = if (isEnabled) PrimaryWhite else PrimaryWhite.copy(alpha = 0.5f)
        )
    }
}