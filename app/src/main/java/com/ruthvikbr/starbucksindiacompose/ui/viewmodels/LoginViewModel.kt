// 此ViewModel處理用戶登錄的業務邏輯
// 它與數據庫交互以驗證用戶憑證，並通過回調通知登錄結果

package com.ruthvikbr.starbucksindiacompose.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    /**
     * 嘗試登錄用戶
     * @param email 用戶的電子郵件
     * @param password 用戶的密碼
     * @param callback 登錄結果的回調函數
     */
    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            // 從數據庫中獲取用戶
            val user = userDao.getUserByEmail(email)
            Log.d("LoginViewModel", "Login attempt for email: $email, user found: ${user != null}")

            // 驗證用戶是否存在且密碼正確
            if (user != null && user.password == password) {
                Log.d("LoginViewModel", "Login successful")
                // 登錄成功
                callback(true)
            } else {
                Log.d("LoginViewModel", "Login failed")
                // 登錄失敗
                callback(false)
            }
        }
    }

    /**
     * 註冊新用戶
     * @param user 要註冊的用戶對象
     * @param callback 註冊結果的回調函數
     */
    fun registerUser(user: User, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // 嘗試將用戶插入數據庫
                userDao.insertUser(user)
                callback(true)
            } catch (e: Exception) {
                // 註冊失敗（可能是電子郵件已存在）
                callback(false)
            }
        }
    }
}