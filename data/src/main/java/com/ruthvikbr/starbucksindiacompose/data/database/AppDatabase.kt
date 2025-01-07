// 此程式碼定義了應用程式的主要資料庫結構，使用 Room 持久化庫。
// 它提供了一個單例模式的資料庫實例，並允許存取 User 相關的資料操作。

package com.ruthvikbr.starbucksindiacompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // 定義抽象方法來獲取 UserDao 介面的實現
    abstract fun userDao(): UserDao

    companion object {
        // 使用 volatile 關鍵字確保 instance 在多執行緒環境下的可見性
        @Volatile
        private var instance: AppDatabase? = null

        // 使用雙重檢查鎖定模式實現單例模式
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // 建立資料庫實例的私有方法
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "starbucks-database")
                .fallbackToDestructiveMigration() // 允許在版本更新時進行破壞性遷移
                .build()
    }
}