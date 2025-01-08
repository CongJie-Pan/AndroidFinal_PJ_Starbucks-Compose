package com.ruthvikbr.starbucksindiacompose.auth

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AuthManager 負責管理用戶的身份驗證狀態。
 * 它提供了設置當前用戶、獲取當前用戶信息、登出功能，以及檢查用戶登錄狀態的方法。
 */
@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val TAG = "AuthManager"
    private val PREFS_NAME = "auth_prefs"
    private val KEY_CURRENT_USER = "current_user_email"

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * 設置當前登錄用戶
     * @param email 用戶的電子郵件地址
     */
    fun setCurrentUser(email: String) {
        Log.d(TAG, "Setting current user: $email")
        sharedPreferences.edit().putString(KEY_CURRENT_USER, email).apply()
        Log.d(TAG, "Current user set successfully. Verifying...")
        val verifiedEmail = getCurrentUserEmail()
        Log.d(TAG, "Verified current user: $verifiedEmail")
    }

    /**
     * 獲取當前登錄用戶的電子郵件
     * @return 當前用戶的電子郵件地址，如果沒有登錄用戶則返回 null
     */
    fun getCurrentUserEmail(): String? {
        val email = sharedPreferences.getString(KEY_CURRENT_USER, null)
        Log.d(TAG, "Getting current user email: $email")
        return email
    }

    /**
     * 登出當前用戶
     */
    fun logout() {
        Log.d(TAG, "Logging out current user")
        sharedPreferences.edit().remove(KEY_CURRENT_USER).apply()
        Log.d(TAG, "Logout completed. Verifying...")
        val verifiedEmail = getCurrentUserEmail()
        Log.d(TAG, "Verified current user after logout: $verifiedEmail")
    }

    /**
     * 檢查是否有用戶當前已登錄
     * @return 如果有用戶登錄則返回 true，否則返回 false
     */
    fun isUserLoggedIn(): Boolean {
        val isLoggedIn = getCurrentUserEmail() != null
        Log.d(TAG, "Checking if user is logged in: $isLoggedIn")
        return isLoggedIn
    }
}