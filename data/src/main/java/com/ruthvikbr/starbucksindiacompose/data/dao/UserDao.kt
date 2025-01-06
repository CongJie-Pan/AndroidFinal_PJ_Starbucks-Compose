// 此程式碼定義了`UserDao`介面，提供插入和查詢`User`資料表的功能。

package com.ruthvikbr.starbucksindiacompose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ruthvikbr.starbucksindiacompose.data.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}