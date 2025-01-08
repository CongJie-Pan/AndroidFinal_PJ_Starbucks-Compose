package com.ruthvikbr.starbucksindiacompose.di

import android.content.Context
import android.util.Log
import com.ruthvikbr.starbucksindiacompose.data.database.AppDatabase
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// DatabaseModule 提供了數據庫相關的依賴注入。
// 它負責創建和提供 AppDatabase 實例以及各種 DAO 實例。

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val TAG = "DatabaseModule"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        Log.d(TAG, "Providing AppDatabase instance")
        return AppDatabase.getInstance(context).also {
            Log.d(TAG, "AppDatabase instance provided")
        }
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        Log.d(TAG, "Providing UserDao")
        return database.userDao().also {
            Log.d(TAG, "UserDao provided")
        }
    }

    // 如果有其他 DAO，可以在這裡添加更多的 Provides 方法
    // 例如：
    // @Provides
    // fun provideProductDao(database: AppDatabase): ProductDao {
    //     Log.d(TAG, "Providing ProductDao")
    //     return database.productDao().also {
    //         Log.d(TAG, "ProductDao provided")
    //     }
    // }
}