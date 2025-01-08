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

/**
 * ProfileViewModel 負責管理使用者個人資料的顯示和更新。
 * 它處理使用者數據的加載、更新和登出操作，並提供了詳細的日誌記錄以便追蹤操作和錯誤。
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao,
    private val authManager: AuthManager
) : ViewModel() {

    private val TAG = "ProfileViewModel"

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        Log.d(TAG, "ProfileViewModel initialized")
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch {
            Log.d(TAG, "Loading user data")
            _isLoading.value = true
            _error.value = null
            try {
                val email = authManager.getCurrentUserEmail()
                if (email != null) {
                    val loadedUser = userDao.getUserByEmail(email)
                    if (loadedUser != null) {
                        Log.d(TAG, "Loaded user: $loadedUser")
                        _user.value = loadedUser
                    } else {
                        Log.w(TAG, "No user found in the database for email: $email")
                        _error.value = "User not found"
                    }
                } else {
                    Log.w(TAG, "No current user email found")
                    _error.value = "No user logged in"
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading user", e)
                _error.value = "Failed to load user data: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateUser(updatedUser: User) {
        viewModelScope.launch {
            Log.d(TAG, "Updating user: $updatedUser")
            _isLoading.value = true
            _error.value = null
            try {
                userDao.updateUser(updatedUser)
                _user.value = updatedUser
                Log.d(TAG, "User updated successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error updating user", e)
                _error.value = "Failed to update user: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            Log.d(TAG, "Logging out user")
            _isLoading.value = true
            _error.value = null
            try {
                authManager.logout()
                _user.value = null
                Log.d(TAG, "User logged out successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error during logout", e)
                _error.value = "Failed to logout: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setCurrentUserAndLoad(email: String) {
        viewModelScope.launch {
            Log.d(TAG, "Setting current user email: $email")
            _isLoading.value = true
            _error.value = null
            try {
                authManager.setCurrentUser(email)
                loadUser()
            } catch (e: Exception) {
                Log.e(TAG, "Error setting current user email", e)
                _error.value = "Failed to set current user: ${e.localizedMessage}"
                _isLoading.value = false
            }
        }
    }

    fun updateAfterAuthentication(email: String) {
        viewModelScope.launch {
            Log.d(TAG, "Updating user data after authentication for email: $email")
            _isLoading.value = true
            _error.value = null
            try {
                setCurrentUserAndLoad(email)
            } catch (e: Exception) {
                Log.e(TAG, "Error updating after authentication", e)
                _error.value = "Failed to update after authentication: ${e.localizedMessage}"
                _isLoading.value = false
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

    fun clearError() {
        _error.value = null
    }
}