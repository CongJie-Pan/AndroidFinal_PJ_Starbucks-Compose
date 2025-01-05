package com.ruthvikbr.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class Member(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val points: Int
)