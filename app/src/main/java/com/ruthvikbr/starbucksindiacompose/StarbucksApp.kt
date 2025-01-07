package com.ruthvikbr.starbucksindiacompose

import android.app.Application
import androidx.room.RoomDatabase
import com.ruthvikbr.starbucksindiacompose.data.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

// 標記此類為 Hilt 的 Android 應用程序入口點
@HiltAndroidApp
class StarbucksApp : Application() {

    // 使用 Hilt 注入 AppDatabase 實例
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        initializeDatabase()
    }

    private fun initializeDatabase() {
        // 在背景線程中初始化數據庫
        // 注意：使用 GlobalScope 應謹慎，這裡僅作為示例
        GlobalScope.launch(Dispatchers.IO) {
            // 通過訪問 writableDatabase 來觸發數據庫的創建或打開
            database.openHelper.writableDatabase
        }
    }
}