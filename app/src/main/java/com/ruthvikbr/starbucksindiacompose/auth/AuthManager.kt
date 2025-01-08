package com.ruthvikbr.starbucksindiacompose.auth

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AuthManager 負責管理使用者的身份驗證狀態。
 * 它提供了設置當前使用者、獲取當前使用者資訊、登出功能，以及檢查使用者登錄狀態的方法。
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
     * 設置當前登錄使用者
     * @param email 使用者的電子郵件地址
     */
    fun setCurrentUser(email: String) {
        Log.d(TAG, "Setting current user: $email")
        sharedPreferences.edit().putString(KEY_CURRENT_USER, email).apply()
        Log.d(TAG, "Current user set successfully. Verifying...")
        val verifiedEmail = getCurrentUserEmail()
        Log.d(TAG, "Verified current user: $verifiedEmail")
    }

    /**
     * 獲取當前登錄使用者的電子郵件
     * @return 當前使用者的電子郵件地址，如果沒有登錄使用者則返回 null
     */
    fun getCurrentUserEmail(): String? {
        val email = sharedPreferences.getString(KEY_CURRENT_USER, null)
        Log.d(TAG, "Getting current user email: $email")
        return email
    }

    /**
     * 登出當前使用者
     */
    fun logout() {
        Log.d(TAG, "Logging out current user")
        sharedPreferences.edit().remove(KEY_CURRENT_USER).apply()
        Log.d(TAG, "Logout completed. Verifying...")
        val verifiedEmail = getCurrentUserEmail()
        Log.d(TAG, "Verified current user after logout: $verifiedEmail")
    }

    /**
     * 檢查是否有使用者當前已登錄
     * @return 如果有使用者登錄則返回 true，否則返回 false
     */
    fun isUserLoggedIn(): Boolean {
        val isLoggedIn = getCurrentUserEmail() != null
        Log.d(TAG, "Checking if user is logged in: $isLoggedIn")
        return isLoggedIn
    }
}