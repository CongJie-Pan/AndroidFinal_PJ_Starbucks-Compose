// 此程式碼碼定義了一個包含 UserDao 的 Room 資料庫，用於 Starbucks India Compose(原程式模板) 應用程式。

package com.ruthvikbr.starbucksindiacompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "starbucks-database")
                .build()
    }
}