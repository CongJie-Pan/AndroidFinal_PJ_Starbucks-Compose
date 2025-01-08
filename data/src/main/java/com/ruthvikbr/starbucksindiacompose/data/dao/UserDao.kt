package com.ruthvikbr.starbucksindiacompose.data.dao

import androidx.room.*
import com.ruthvikbr.starbucksindiacompose.data.entity.User

/**
 * UserDao 接口定義了與用戶相關的數據庫操作。
 * Room 會自動生成這些方法的實現。
 */
@Dao
interface UserDao {
    /**
     * 插入或更新用戶
     * @param user 要插入或更新的用戶對象
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * 根據電子郵件獲取用戶
     * @param email 用戶的電子郵件
     * @return 返回用戶對象，如果沒有找到則返回 null
     */
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    /**
     * 獲取所有用戶
     * @return 返回所有用戶的列表
     */
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    /**
     * 更新用戶信息
     * @param user 更新後的用戶對象
     */
    @Update
    suspend fun updateUser(user: User)

    /**
     * 刪除用戶
     * @param email 要刪除的用戶的電子郵件
     */
    @Query("DELETE FROM users WHERE email = :email")
    suspend fun deleteUser(email: String)

    /**
     * 檢查用戶是否存在
     * @param email 要檢查的用戶電子郵件
     * @return 如果用戶存在返回 true，否則返回 false
     */
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    suspend fun userExists(email: String): Boolean
}