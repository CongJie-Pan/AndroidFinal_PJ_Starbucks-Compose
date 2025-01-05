package com.ruthvikbr.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ruthvikbr.data.models.OrderItem
import kotlinx.coroutines.flow.Flow
import com.ruthvikbr.data.models.Member
import androidx.room.*

@Dao
interface StarbucksDao {

    @Query("SELECT * FROM order_items")
    suspend fun getAllOrderItems(): List<OrderItem>

    @Insert
    fun insertOrderItem(orderItem: OrderItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItems(orderItems: List<OrderItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrderItem(orderItem: OrderItem)

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)

    @Query("SELECT * FROM `order_items` WHERE itemCategory= :activeCategory")
    fun getOrderItems(activeCategory: String): Flow<List<OrderItem>>

    @Query("SELECT * FROM `order_items` WHERE itemCount >= 1")
    fun getCartItems(): Flow<List<OrderItem>>

    @Query("UPDATE `order_items` SET itemCount = 0 WHERE itemCount >= 1")
    suspend fun deleteAllCartItems()
}
