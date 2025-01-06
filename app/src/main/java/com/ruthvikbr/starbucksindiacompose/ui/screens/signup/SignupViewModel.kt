// 此ViewModel負責用戶註冊邏輯，註冊用戶並處理成功或失敗的回調
package com.ruthvikbr.starbucksindiacompose.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

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
            val user = User(
                email = email,
                firstName = firstName,
                lastName = lastName,
                birthday = birthday,
                mobileNumber = mobileNumber
            )
            userDao.insertUser(user)
        }
    }
}