/**
 * 此檔案實現了使用者資料的 ViewModel，負責管理使用者的狀態。
 * 使用 Hilt 進行依賴注入，確保 ViewModel 能夠存取資料庫。
 * 透過 StateFlow 實現響應式的資料流，提供使用者資料的即時更新。
 */
package com.ruthvikbr.starbucksindiacompose.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel  // 使用 Hilt 進行依賴注入的 ViewModel 標註
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao  // 注入 UserDao 用於存取資料庫中的使用者資料
) : ViewModel() {

    // 使用 MutableStateFlow 作為可變的狀態容器，初始值為 null
    private val _user = MutableStateFlow<User?>(null)
    // 對外暴露不可變的 StateFlow，確保資料安全性
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        // 初始化時載入使用者資料
        loadUser()
    }

    private fun loadUser() {
        // 在 ViewModel 的作用域內啟動協程
        viewModelScope.launch {
            // 從資料庫獲取所有使用者並取得第一個使用者
            // 此設計假設系統中只存在一個使用者
            _user.value = userDao.getAllUsers().firstOrNull()
        }
    }
}
