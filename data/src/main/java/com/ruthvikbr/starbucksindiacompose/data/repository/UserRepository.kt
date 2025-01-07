// 此類別作為用戶數據的存儲庫，提供了一個抽象層來訪問用戶數據。
// 它封裝了數據訪問的邏輯，使得 ViewModel 可以更方便地操作用戶數據。

package com.ruthvikbr.starbucksindiacompose.data.repository

import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    /**
     * 插入或更新用戶
     * @param user 要插入或更新的用戶對象
     */
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    /**
     * 根據電子郵件地址獲取用戶
     * @param email 用戶的電子郵件地址
     * @return 返回匹配的用戶對象，如果沒有找到則返回 null
     */
    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    /**
     * 獲取所有用戶
     * @return 返回所有用戶的列表
     */
    suspend fun getAllUsers() = userDao.getAllUsers()
}