/*
 * 此ViewModel用於處理用戶註冊相關的業務邏輯
 * 負責將用戶註冊資訊保存到本地數據庫
 * 使用Hilt進行依賴注入，確保UserDao的正確注入和使用
 */

package com.ruthvikbr.starbucksindiacompose.ui.screens.signup

// 導入必要的類和依賴
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// 使用Hilt注入的ViewModel
@HiltViewModel
class SignupViewModel @Inject constructor(
    // 注入UserDao用於數據庫操作
    private val userDao: UserDao
) : ViewModel() {

    /**
     * 保存用戶信息到數據庫
     * @param email 用戶郵箱
     * @param password 用戶密碼
     * @param mobileNumber 手機號碼
     * @param firstName 名字
     * @param lastName 姓氏
     * @param birthday 生日
     * @param referralCode 推薦碼
     * @param isSmsEnabled 是否開啟簡訊通知
     * @param isEmailEnabled 是否開啟郵件通知
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
        // 在協程範圍內執行數據庫操作
        viewModelScope.launch {
            // 創建用戶實體
            val user = User(
                email = email,
                firstName = firstName,
                lastName = lastName,
                birthday = birthday,
                mobileNumber = mobileNumber
            )
            // 將用戶信息插入數據庫
            userDao.insertUser(user)
        }
    }
}