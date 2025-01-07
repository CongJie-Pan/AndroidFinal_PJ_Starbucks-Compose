// 此代碼定義了應用程序的入口點，並初始化了必要的組件。
// 它使用 Hilt 進行依賴注入，並在應用啟動時初始化數據庫。

package com.ruthvikbr.starbucksindiacompose

import android.app.Application
import android.util.Log
import com.ruthvikbr.starbucksindiacompose.data.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// 使用 @HiltAndroidApp 註解來啟用 Hilt 的依賴注入
@HiltAndroidApp
class StarbucksApp : Application() {

    // 使用 Hilt 注入 AppDatabase 實例
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        initializeDatabase()
        // 設置全局異常處理器
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("StarbucksApp", "Uncaught exception in thread ${thread.name}", throwable)
        }
    }

    // 初始化數據庫的私有方法
    private fun initializeDatabase() {
        // 使用協程在 IO 線程中初始化數據庫
        CoroutineScope(Dispatchers.IO).launch {
            // 通過訪問 writableDatabase 來觸發數據庫的創建或打開
            database.openHelper.writableDatabase
        }
    }
}