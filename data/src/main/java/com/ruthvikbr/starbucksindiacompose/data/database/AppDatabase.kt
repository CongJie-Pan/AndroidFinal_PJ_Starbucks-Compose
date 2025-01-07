// 此代碼定義了應用程序的主要數據庫結構，使用 Room 持久化庫。
// 它提供了一個單例模式的數據庫實例，並允許訪問 User 相關的數據操作。

package com.ruthvikbr.starbucksindiacompose.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import com.ruthvikbr.starbucksindiacompose.data.entity.User

// 使用 @Database 註解定義資料庫，指定實體類和版本號
@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // 定義抽象方法來獲取 UserDao 接口的實現
    abstract fun userDao(): UserDao

    companion object {
        private const val TAG = "AppDatabase"

        // 使用 volatile 關鍵字確保 instance 在多線程環境下的可見性
        @Volatile
        private var instance: AppDatabase? = null

        // 使用雙重檢查鎖定模式實現單例模式
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // 建立數據庫實例的私有方法
        private fun buildDatabase(context: Context): AppDatabase {
            Log.d(TAG, "Building database instance")
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "starbucks-database")
                .fallbackToDestructiveMigration() // 允許在版本更新時進行破壞性遷移
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.i(TAG, "Database created")
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        Log.i(TAG, "Database opened")
                    }
                })
                .build()
                .also { Log.d(TAG, "Database instance built") }
        }
    }
}