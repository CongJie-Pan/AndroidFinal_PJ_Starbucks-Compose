// ProfileViewModel 負責管理用戶資料的狀態和相關操作。
// 它處理用戶資料的載入、更新、登出功能，並確保在用戶認證後立即更新資料。

package com.ruthvikbr.starbucksindiacompose.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ProfileViewModel"

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao,
    private val authManager: AuthManager
) : ViewModel() {

    // 內部可變的用戶狀態流
    private val _user = MutableStateFlow<User?>(null)
    // 對外暴露的不可變用戶狀態流
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        Log.d(TAG, "ProfileViewModel initialized")
        // 初始化時載入用戶數據
        loadUser()
    }

    /**
     * 載入當前登錄用戶的數據
     * 此方法會在 ViewModel 初始化時和每次需要刷新用戶數據時被調用
     */
    fun loadUser() {
        viewModelScope.launch {
            Log.d(TAG, "Loading user data")
            val currentUserEmail = authManager.getCurrentUserEmail()
            Log.d(TAG, "Current user email: $currentUserEmail")
            if (currentUserEmail != null) {
                val loadedUser = userDao.getUserByEmail(currentUserEmail)
                if (loadedUser != null) {
                    Log.d(TAG, "Loaded user: $loadedUser")
                    _user.value = loadedUser
                } else {
                    Log.w(TAG, "User not found for email: $currentUserEmail")
                    _user.value = null
                }
            } else {
                Log.w(TAG, "No current user email found")
                _user.value = null
            }
        }
    }

    /**
     * 更新用戶資料
     * @param updatedUser 更新後的用戶對象
     */
    fun updateUser(updatedUser: User) {
        viewModelScope.launch {
            Log.d(TAG, "Updating user: $updatedUser")
            try {
                userDao.insertUser(updatedUser)
                _user.value = updatedUser
                Log.d(TAG, "User updated successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error updating user", e)
            }
        }
    }

    /**
     * 登出當前用戶
     */
    fun logout() {
        viewModelScope.launch {
            Log.d(TAG, "Logging out user")
            try {
                // 清除身份驗證狀態
                authManager.logout()
                // 清除當前用戶數據
                _user.value = null
                Log.d(TAG, "User logged out successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error during logout", e)
            }
        }
    }

    /**
     * 設置當前用戶的電子郵件並加載用戶數據
     * @param email 當前用戶的電子郵件
     */
    fun setCurrentUserAndLoad(email: String) {
        viewModelScope.launch {
            Log.d(TAG, "Setting current user email: $email")
            try {
                authManager.setCurrentUser(email)  // 使用正確的方法名
                loadUser()
            } catch (e: Exception) {
                Log.e(TAG, "Error setting current user email", e)
            }
        }
    }

    /**
     * 在用戶認證（註冊或登錄）後立即更新用戶資料
     * @param email 新認證用戶的郵箱
     */
    fun updateAfterAuthentication(email: String) {
        viewModelScope.launch {
            Log.d(TAG, "Updating user data after authentication for email: $email")
            try {
                setCurrentUserAndLoad(email)
            } catch (e: Exception) {
                Log.e(TAG, "Error updating after authentication", e)
            }
        }
    }

    /**
     * 檢查用戶是否已登錄
     * @return 返回一個布爾值，表示用戶是否已登錄
     */
    fun isUserLoggedIn(): Boolean {
        val isLoggedIn = authManager.getCurrentUserEmail() != null
        Log.d(TAG, "Checking if user is logged in: $isLoggedIn")
        return isLoggedIn
    }

    /**
     * 獲取當前用戶的電子郵件
     * @return 返回當前用戶的電子郵件，如果未登錄則返回 null
     */
    fun getCurrentUserEmail(): String? {
        val email = authManager.getCurrentUserEmail()
        Log.d(TAG, "Getting current user email: $email")
        return email
    }
}