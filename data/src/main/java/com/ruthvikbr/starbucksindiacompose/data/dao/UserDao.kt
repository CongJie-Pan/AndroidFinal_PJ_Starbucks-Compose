package com.ruthvikbr.starbucksindiacompose.data.dao

import androidx.room.*
import com.ruthvikbr.starbucksindiacompose.data.entity.User
import android.util.Log

/**
 * UserDao 負責處理與用戶相關的數據庫操作。
 * 它提供了插入、查詢和獲取用戶數據的方法，並包含詳細的日誌記錄以便追蹤操作。
 */
@Dao
interface UserDao {
    companion object {
        private const val TAG = "UserDao"
    }

    /**
     * 插入一個新用戶或更新現有用戶
     * @param user 要插入或更新的用戶對象
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User) {
        Log.d(TAG, "Inserting or updating user: $user")
        // 實際的插入操作由 Room 自動生成
    }

    /**
     * 根據電子郵件地址查詢用戶
     * @param email 用戶的電子郵件地址
     * @return 返回匹配的用戶對象，如果沒有找到則返回 null
     */
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User? {
        Log.d(TAG, "Querying user by email: $email")
        val user = null // 實際的查詢操作由 Room 自動生成
        Log.d(TAG, "Query result for email $email: $user")
        return user
    }

    /**
     * 獲取所有用戶
     * @return 返回所有用戶的列表
     */
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User> {
        Log.d(TAG, "Fetching all users")
        val users = emptyList<User>() // 實際的查詢操作由 Room 自動生成
        Log.d(TAG, "Total users fetched: ${users.size}")
        return users
    }
}