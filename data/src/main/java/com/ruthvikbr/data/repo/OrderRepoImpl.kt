package com.ruthvikbr.data.repo

import com.ruthvikbr.data.local.StarbucksDao
import com.ruthvikbr.data.models.mappers.toDmOrderItem
import com.ruthvikbr.data.models.mappers.toOrderItem
import com.ruthvikbr.domain.models.DMOrderItem
import com.ruthvikbr.domain.repo.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepoImpl @Inject constructor(private val dao: StarbucksDao) : OrderRepository {
    override fun getOrderItems(): Flow<List<DMOrderItem>> {
        return dao.getOrderItems().map {
            it.map { item ->
                item.toDmOrderItem()
            }
        }
    }

    override fun getCartItems(): Flow<List<DMOrderItem>> {
        return dao.getCartItems().map {
            it.map { item ->
                item.toDmOrderItem()
            }
        }
    }

    override suspend fun updateOrderItem(dmOrderItem: DMOrderItem) {
        dao.updateOrderItem(dmOrderItem.toOrderItem())
    }

    override suspend fun deleteAllCartItems() {
        dao.deleteAllCartItems()
    }
}
