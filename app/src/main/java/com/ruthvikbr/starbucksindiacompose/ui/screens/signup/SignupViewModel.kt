package com.ruthvikbr.starbucksindiacompose.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.auth.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log

/**
 * SignupViewModel 負責處理使用者註冊過程中的業務邏輯。
 * 它管理使用者數據的保存，驗證輸入，並在註冊成功後設置當前使用者。
 */
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userDao: UserDao,
    private val authManager: AuthManager
) : ViewModel() {

    private val TAG = "SignupViewModel"

    /**
     * 保存使用者資訊到資料庫並設置當前使用者
     */
    fun saveUser(
        email: String,
        password: String,
        mobileNumber: String,
        firstName: String,
        lastName: String,
        birthday: String,
        referralCode: String,
        isSmsEnabled: Boolean,
        isEmailEnabled: Boolean
    ) {
        viewModelScope.launch {
            Log.d(TAG, "Attempting to save user: $email")

            // 驗證輸入
            if (!validateInput(email, password, mobileNumber, firstName, lastName, birthday)) {
                Log.e(TAG, "Invalid input data")
                return@launch
            }

            val user = User(
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                birthday = birthday,
                mobileNumber = mobileNumber,
                referralCode = referralCode,
                isSmsEnabled = isSmsEnabled,
                isEmailEnabled = isEmailEnabled
            )

            try {
                // 保存使用者到資料庫
                userDao.insertUser(user)
                Log.d(TAG, "User saved to database successfully")

                // 設置當前使用者
                authManager.setCurrentUser(email)
                Log.d(TAG, "Current user set in AuthManager")

                // 驗證使用者是否成功保存和設置
                val savedUser = userDao.getUserByEmail(email)
                Log.d(TAG, "Verified saved user: $savedUser")
                val currentUserEmail = authManager.getCurrentUserEmail()
                Log.d(TAG, "Verified current user email: $currentUserEmail")

            } catch (e: Exception) {
                Log.e(TAG, "Error saving user: ${e.message}", e)
            }
        }
    }

    /**
     * 驗證使用者輸入的數據
     */
    private fun validateInput(
        email: String,
        password: String,
        mobileNumber: String,
        firstName: String,
        lastName: String,
        birthday: String
    ): Boolean {
        if (email.isBlank() || password.isBlank() || mobileNumber.isBlank() ||
            firstName.isBlank() || lastName.isBlank() || birthday.isBlank()
        ) {
            Log.w(TAG, "One or more required fields are empty")
            return false
        }
        // 可以添加更多驗證，如電子郵件格式、密碼強度等
        Log.d(TAG, "Input validation passed")
        return true
    }
}