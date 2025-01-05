package com.ruthvikbr.starbucksindiacompose

import android.app.Application
import com.ruthvikbr.data.local.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StarbucksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDatabase()
    }

    private fun initializeDatabase() {
        // 初始化資料庫
        DatabaseInitializer.getDatabase(this)
    }
}