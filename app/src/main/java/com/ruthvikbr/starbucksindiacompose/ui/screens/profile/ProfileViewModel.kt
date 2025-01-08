package com.ruthvikbr.starbucksindiacompose.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.auth.AuthManager
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ProfileViewModel 負責管理用戶個人資料的顯示和更新。
 * 它處理用戶數據的加載、更新和登出操作，並提供了詳細的日誌記錄以便追蹤操作和錯誤。
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao,
    private val authManager: AuthManager
) : ViewModel() {

    private val TAG = "ProfileViewModel"

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        Log.d(TAG, "ProfileViewModel initialized")
        loadUser()
    }

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

    fun logout() {
        viewModelScope.launch {
            Log.d(TAG, "Logging out user")
            try {
                authManager.logout()
                _user.value = null
                Log.d(TAG, "User logged out successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error during logout", e)
            }
        }
    }

    fun setCurrentUserAndLoad(email: String) {
        viewModelScope.launch {
            Log.d(TAG, "Setting current user email: $email")
            try {
                authManager.setCurrentUser(email)
                loadUser()
            } catch (e: Exception) {
                Log.e(TAG, "Error setting current user email", e)
            }
        }
    }

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

    fun isUserLoggedIn(): Boolean {
        val isLoggedIn = authManager.getCurrentUserEmail() != null
        Log.d(TAG, "Checking if user is logged in: $isLoggedIn")
        return isLoggedIn
    }

    fun getCurrentUserEmail(): String? {
        val email = authManager.getCurrentUserEmail()
        Log.d(TAG, "Getting current user email: $email")
        return email
    }
}