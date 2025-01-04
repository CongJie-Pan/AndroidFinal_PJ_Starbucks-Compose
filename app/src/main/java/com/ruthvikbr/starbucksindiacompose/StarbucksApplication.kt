package com.ruthvikbr.starbucksindiacompose

import android.app.Application
import com.ruthvikbr.data.local.DatabaseInitializer

class StarbucksApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseInitializer.getDatabase(this)
    }
}