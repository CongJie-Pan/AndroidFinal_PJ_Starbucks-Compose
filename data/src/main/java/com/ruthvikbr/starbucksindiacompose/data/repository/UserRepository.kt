package com.ruthvikbr.starbucksindiacompose.data.repository

import android.util.Log
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    private val TAG = "UserRepository"

    /**
     * 插入或更新使用者
     * @param user 要插入或更新的使用者對象
     */
    suspend fun insertUser(user: User) {
        try {
            userDao.insertUser(user)
            Log.d(TAG, "User inserted/updated successfully: $user")
        } catch (e: Exception) {
            Log.e(TAG, "Error inserting/updating user: ${e.message}", e)
        }
    }

    /**
     * 獲取使用者
     * @param email 使用者的電子郵件地址
     * @return 返回使用者對象，如果沒有使用者則返回 null
     */
    suspend fun getUser(email: String): User? {
        return try {
            val user = userDao.getUserByEmail(email)
            Log.d(TAG, "Retrieved user: $user")
            user
        } catch (e: Exception) {
            Log.e(TAG, "Error retrieving user: ${e.message}", e)
            null
        }
    }

    /**
     * 更新使用者資訊
     * @param user 更新後的使用者對象
     */
    suspend fun updateUser(user: User) {
        try {
            userDao.updateUser(user)
            Log.d(TAG, "User updated successfully: $user")
        } catch (e: Exception) {
            Log.e(TAG, "Error updating user: ${e.message}", e)
        }
    }

    /**
     * 刪除使用者
     * @param email 要刪除的使用者的電子郵件地址
     */
    suspend fun deleteUser(email: String) {
        try {
            userDao.deleteUser(email)
            Log.d(TAG, "User deleted successfully for email: $email")
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting user: ${e.message}", e)
        }
    }

    /**
     * 檢查是否存在使用者
     * @param email 要檢查的使用者的電子郵件地址
     * @return 如果存在使用者則返回 true，否則返回 false
     */
    suspend fun userExists(email: String): Boolean {
        return try {
            val exists = userDao.userExists(email)
            Log.d(TAG, "User exists for email $email: $exists")
            exists
        } catch (e: Exception) {
            Log.e(TAG, "Error checking if user exists: ${e.message}", e)
            false
        }
    }
}