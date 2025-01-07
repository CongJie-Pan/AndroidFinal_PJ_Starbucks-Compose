// 此程式碼定義了一個 Hilt 依賴注入模組，用於提供資料庫相關的依賴。
// 它確保了 AppDatabase 和 UserDao 的單例實例在整個應用程式中可用。

package com.ruthvikbr.starbucksindiacompose.di

import android.content.Context
import androidx.room.Room
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

    // 提供 AppDatabase 實例
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "starbucks-database" // 資料庫名稱
        )
            .fallbackToDestructiveMigration() // 允許在版本更新時進行破壞性遷移
            .build()
    }

    // 提供 UserDao 實例
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    // 可以在這裡添加其他 DAO 的 Provides 方法
    // 例如：
    // @Provides
    // fun provideProductDao(database: AppDatabase): ProductDao {
    //     return database.productDao()
    // }
}