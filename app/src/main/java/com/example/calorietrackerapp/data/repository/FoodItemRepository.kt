package com.example.calorietrackerapp.data.repository

import com.example.calorietrackerapp.data.entity.FoodItem
import kotlinx.coroutines.flow.Flow

interface FoodItemRepository {
    fun getAllFoodItems(): Flow<List<FoodItem>>
    fun getFoodItemById(id: Int): Flow<FoodItem>
    fun searchFoodByName(query: String): Flow<List<FoodItem>>
    suspend fun insertFoodItem(item: FoodItem)
    suspend fun updateFoodItem(item: FoodItem)
    suspend fun deleteFoodItem(item: FoodItem)
}