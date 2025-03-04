package com.ruthvikbr.starbucksindiacompose.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * LoginViewModel 負責處理使用者登錄和註冊的業務邏輯。
 * 它驗證使用者憑證，管理登錄狀態，並處理新使用者的註冊過程。
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDao: UserDao,
    private val authManager: AuthManager
) : ViewModel() {

    private val TAG = "LoginViewModel"

    /**
     * 嘗試登錄使用者
     * @param email 使用者的電子郵件
     * @param password 使用者的密碼
     * @param callback 登錄結果的回調函數
     */
    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            Log.d(TAG, "Login attempt for email: $email")
            // 從資料庫中獲取使用者
            val user = userDao.getUserByEmail(email)

            // 驗證使用者是否存在且密碼正確
            if (user != null && user.email == email && user.password == password) {
                Log.d(TAG, "Login successful")
                authManager.setCurrentUser(email)
                callback(true)
            } else {
                Log.d(TAG, "Login failed")
                callback(false)
            }
        }
    }

    /**
     * 註冊新使用者
     * @param user 要註冊的使用者對象
     * @param callback 註冊結果的回調函數
     */
    fun registerUser(user: User, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Attempting to register new user: ${user.email}")
                // 嘗試將使用者插入資料庫
                userDao.insertUser(user)
                authManager.setCurrentUser(user.email)
                Log.d(TAG, "User registered successfully")
                callback(true)
            } catch (e: Exception) {
                // 註冊失敗（可能是電子郵件已存在）
                Log.e(TAG, "Registration failed: ${e.message}", e)
                callback(false)
            }
        }
    }
}