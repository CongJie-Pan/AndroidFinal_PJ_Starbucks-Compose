/**
 * Hilt 模塊：DatabaseModule
 * 此檔案定義了應用程式的資料庫依賴注入模組。
 * 使用 Hilt 框架提供資料庫相關的單例實例。
 * 確保整個應用程式使用同一個資料庫實例，提高效能和資源利用。
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

@Module  // 標註此類別為 Hilt 的依賴注入模組
@InstallIn(SingletonComponent::class)  // 在應用程式級別安裝此模組
object DatabaseModule {

    @Provides  // 標註此方法提供依賴
    @Singleton  // 確保資料庫實例為單例模式
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        // 使用應用程式上下文創建或獲取資料庫實例
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        // 提供資料庫的 UserDao 實例，用於存取使用者相關資料
        return database.userDao()
    }
}