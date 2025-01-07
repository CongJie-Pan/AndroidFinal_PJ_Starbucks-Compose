// 此 ViewModel 負責管理用戶資料的狀態，並提供介面給 UI 層使用。
// 它使用 Hilt 進行依賴注入，並通過 StateFlow 實現響應式的數據流管理。

package com.ruthvikbr.starbucksindiacompose.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        // 初始化時載入用戶數據
        loadUser()
    }

    /**
     * 載入當前登錄用戶的數據
     */
    private fun loadUser() {
        viewModelScope.launch {
            val currentUserEmail = authManager.getCurrentUserEmail()
            currentUserEmail?.let { email ->
                // 根據電子郵件地址從數據庫獲取用戶
                _user.value = userDao.getUserByEmail(email)
            }
        }
    }

    /**
     * 更新用戶資料
     * @param updatedUser 更新後的用戶對象
     */
    fun updateUser(updatedUser: User) {
        viewModelScope.launch {
            userDao.insertUser(updatedUser)
            _user.value = updatedUser
        }
    }

    /**
     * 登出當前用戶
     */
    fun logout() {
        viewModelScope.launch {
            authManager.logout()
            _user.value = null
        }
    }
}