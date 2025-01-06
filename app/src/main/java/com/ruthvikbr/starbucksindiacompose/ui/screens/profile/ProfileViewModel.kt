// 這個檔案定義了用於管理使用者資料的 ProfileViewModel 類別
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

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            // 這裡假設我們只有一個用戶，所以我們獲取第一個用戶
            _user.value = userDao.getAllUsers().firstOrNull()
        }
    }
}
