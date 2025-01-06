/**
 * Hilt 模塊：DatabaseModule
 * 
 * 此模塊負責提供應用程式中數據庫相關的依賴項。
 * 它使用 Hilt 的依賴注入框架來提供 AppDatabase 和 UserDao 的單例實例，
 * 確保整個應用程序中使用相同的數據庫實例，並提供訪問用戶數據的 DAO。
 */
package com.ruthvikbr.starbucksindiacompose.di

import android.content.Context
import com.ruthvikbr.starbucksindiacompose.data.database.AppDatabase
import com.ruthvikbr.starbucksindiacompose.data.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}