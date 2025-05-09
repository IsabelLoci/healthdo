package com.example.calorietrackerapp.data.repository

import com.example.calorietrackerapp.data.dao.FoodItemDao
import com.example.calorietrackerapp.data.entity.FoodItem
import kotlinx.coroutines.flow.Flow

class OfflineFoodItemRepository(private val dao: FoodItemDao) :FoodItemRepository {

    override fun getAllFoodItems() = dao.getAllFoodItems()
    override fun getFoodItemById(id: Int) = dao.getFoodItemById(id)
    override fun searchFoodByName(query: String) = dao.searchFoodByName(query)
    override suspend fun insertFoodItem(item: FoodItem) = dao.insertFood(item)
    override suspend fun updateFoodItem(item: FoodItem) = dao.updateFood(item)
    override suspend fun deleteFoodItem(item: FoodItem) = dao.deleteFood(item)

}