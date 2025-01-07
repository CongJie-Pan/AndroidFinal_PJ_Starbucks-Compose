// 定義一個名為 User 的資料類別，並將其標註為 Room 資料庫中的一個實體

package com.ruthvikbr.starbucksindiacompose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val mobileNumber: String,
    val referralCode: String,
    val isSmsEnabled: Boolean,
    val isEmailEnabled: Boolean
)