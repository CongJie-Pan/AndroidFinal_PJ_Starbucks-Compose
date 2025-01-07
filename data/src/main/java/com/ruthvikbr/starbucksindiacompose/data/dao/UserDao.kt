// 此介面定義了與用戶資料相關的數據庫操作。
// 它提供了插入、查詢單個用戶和獲取所有用戶的方法，作為 Room 數據庫的數據訪問對象。

package com.ruthvikbr.starbucksindiacompose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruthvikbr.starbucksindiacompose.data.entity.User

@Dao // 標記這個介面為 Room 的 DAO（數據訪問對象）
interface UserDao {

    /**
     * 插入一個新用戶或更新現有用戶
     * @param user 要插入或更新的用戶對象
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * 根據電子郵件地址查詢用戶
     * @param email 用戶的電子郵件地址
     * @return 返回匹配的用戶對象，如果沒有找到則返回 null
     */
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    /**
     * 獲取所有用戶
     * @return 返回所有用戶的列表
     */
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}