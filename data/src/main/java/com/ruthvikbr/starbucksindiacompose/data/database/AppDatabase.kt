// 此程式碼碼定義了一個包含 UserDao 的 Room 資料庫，用於 Starbucks India Compose(原程式模板) 應用程式。

package com.ruthvikbr.starbucksindiacompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User

// 此類別是應用程式的主要資料庫類別，使用 Room 持久化函式庫
// 註解 @Database 指定了資料庫包含的實體類別和版本號
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // 抽象方法，用於獲取 UserDao 介面的實現
    abstract fun userDao(): UserDao

    companion object {
        // 使用 volatile 關鍵字確保 instance 在多執行緒環境下的可見性
        private var instance: AppDatabase? = null

        // 使用雙重檢查鎖定模式實現單例模式
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // 建立資料庫實例的私有方法
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "starbucks-database")
                .build()
    }
}