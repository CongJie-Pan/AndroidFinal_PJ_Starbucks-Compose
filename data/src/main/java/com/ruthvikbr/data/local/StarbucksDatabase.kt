package com.ruthvikbr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ruthvikbr.data.models.Member
import com.ruthvikbr.data.models.OrderItem

@Database(entities = [OrderItem::class, Member::class], version = 2, exportSchema = false)
abstract class StarbucksDatabase : RoomDatabase() {
    abstract fun starbucksDao(): StarbucksDao
}
