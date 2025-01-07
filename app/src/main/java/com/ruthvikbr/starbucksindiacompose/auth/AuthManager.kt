// 此類別負責管理用戶的身份驗證狀態，包括登入、登出和獲取當前用戶信息。
// 它使用 SharedPreferences 來持久化存儲用戶的登錄狀態。

package com.ruthvikbr.starbucksindiacompose.auth

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // 使用 SharedPreferences 來存儲身份驗證相關的數據
    private val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    /**
     * 設置當前登錄用戶的電子郵件
     * @param email 用戶的電子郵件地址
     */
    fun setCurrentUser(email: String) {
        sharedPreferences.edit().putString("current_user_email", email).apply()
    }

    /**
     * 獲取當前登錄用戶的電子郵件
     * @return 當前用戶的電子郵件地址，如果沒有登錄用戶則返回 null
     */
    fun getCurrentUserEmail(): String? {
        return sharedPreferences.getString("current_user_email", null)
    }

    /**
     * 登出當前用戶
     */
    fun logout() {
        sharedPreferences.edit().remove("current_user_email").apply()
    }

    /**
     * 檢查用戶是否已登錄
     * @return 如果用戶已登錄則返回 true，否則返回 false
     */
    fun isUserLoggedIn(): Boolean {
        return getCurrentUserEmail() != null
    }
}