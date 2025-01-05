package com.ruthvikbr.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ruthvikbr.data.models.OrderItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseInitializer {
    private var instance: StarbucksDatabase? = null

    fun getDatabase(context: Context): StarbucksDatabase {
        return instance ?: synchronized(this) {
            val newInstance = Room.databaseBuilder(
                context.applicationContext,
                StarbucksDatabase::class.java,
                "starbucks_database"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            instance?.starbucksDao()?.insertOrderItems(OrderMenuItems.orderItemsList)
                        }
                    }
                })
                .fallbackToDestructiveMigration() // 在開發中使用，生產環境應該使用適當的遷移策略
                .build()
            instance = newInstance
            newInstance
        }
    }
}