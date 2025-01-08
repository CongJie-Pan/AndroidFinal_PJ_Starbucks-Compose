/*
 * 此組件負責顯示使用者的詳細個人資料，包括頭像、姓名、聯絡資訊等。
 * 它使用 ViewModel 來管理和獲取使用者數據，並以響應式方式更新 UI。
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
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ProfileDetails(viewModel: ProfileViewModel = hiltViewModel(),user: User?) {
    // 使用 collectAsState 來觀察使用者數據的變化
    val user = viewModel.user.collectAsState().value

    Log.d("ProfileDetails", "Collected user: $user")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // 顯示使用者頭像
        ProfilePicture()

        SpacerComponent(spaceInDp = 16.dp)

        // 顯示使用者全名
        Text(
            text = "${user?.firstName} ${user?.lastName}",
            style = MaterialTheme.typography.h4,
            color = PrimaryWhite,
            fontWeight = FontWeight.Bold
        )

        SpacerComponent(spaceInDp = 4.dp)

        // 顯示歡迎訊息
        Text(
            text = "您好 ${user?.firstName}",
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Normal),
            color = PrimaryWhite
        )

        SpacerComponent(spaceInDp = 16.dp)

        // 顯示使用者聯絡資訊
        UserInfoItem(label = "電子郵件", value = user?.email)
        UserInfoItem(label = "手機號碼", value = user?.mobileNumber)

        // 顯示使用者生日，改善顯示格式
        UserInfoItem(label = "生日", value = user?.birthday?.let { formatDate(it) })

        // 顯示使用者偏好設定
        SpacerComponent(spaceInDp = 8.dp)
        Text(
            text = "您的訊息偏好設定",
            style = MaterialTheme.typography.h6,
            color = PrimaryWhite,
            fontWeight = FontWeight.Bold
        )
        UserPreferenceItem(label = "SMS 即時訊息開啟", isEnabled = user?.isSmsEnabled ?: false)
        UserPreferenceItem(label = "電子郵件訊息開啟", isEnabled = user?.isEmailEnabled ?: false)
    }
}

// 改善生日顯示格式
fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        outputFormat.format(date)
    } catch (e: Exception) {
        "Invalid date"
    }
}

// 定義了一個名為UserInfoItem的Composable函數,用於顯示使用者的資訊項目。
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

// 此 UserPreferenceItem Composable函數用於顯示使用者偏好設定項目,並根據啟用狀態顯示相應的文本和顏色。
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