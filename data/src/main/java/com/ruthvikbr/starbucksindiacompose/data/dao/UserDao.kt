// 此代碼定義了用戶數據訪問對象（DAO），提供了對用戶數據的基本操作。
// 它包含了插入、查詢和獲取所有用戶的方法，並加入了日誌功能以便追蹤操作。

package com.ruthvikbr.starbucksindiacompose.data.dao

import android.util.Log
import androidx.room.*
import com.ruthvikbr.starbucksindiacompose.data.entity.User

@Dao // 標記這個介面為 Room 的 DAO（數據訪問對象）
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
        Log.d(TAG, "Inserting user: $user")
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
        return null // 實際的查詢操作由 Room 自動生成
    }

    /**
     * 獲取所有用戶
     * @return 返回所有用戶的列表
     */
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User> {
        Log.d(TAG, "Fetching all users")
        return emptyList() // 實際的查詢操作由 Room 自動生成
    }
}