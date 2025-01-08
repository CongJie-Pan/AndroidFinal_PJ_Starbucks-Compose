package com.ruthvikbr.starbucksindiacompose.data.dao

import androidx.room.*
import com.ruthvikbr.starbucksindiacompose.data.entity.User

/**
 * UserDao 接口定義了與使用者相關的資料庫操作。
 * Room 會自動生成這些方法的實現。
 */
@Dao
interface UserDao {
    /**
     * 插入或更新使用者
     * @param user 要插入或更新的使用者對象
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * 根據電子郵件獲取使用者
     * @param email 使用者的電子郵件
     * @return 返回使用者對象，如果沒有找到則返回 null
     */
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    /**
     * 獲取所有使用者
     * @return 返回所有使用者的列表
     */
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    /**
     * 更新使用者資訊
     * @param user 更新後的使用者對象
     */
    @Update
    suspend fun updateUser(user: User)

    /**
     * 刪除使用者
     * @param email 要刪除的使用者的電子郵件
     */
    @Query("DELETE FROM users WHERE email = :email")
    suspend fun deleteUser(email: String)

    /**
     * 檢查使用者是否存在
     * @param email 要檢查的使用者電子郵件
     * @return 如果使用者存在返回 true，否則返回 false
     */
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    suspend fun userExists(email: String): Boolean
}