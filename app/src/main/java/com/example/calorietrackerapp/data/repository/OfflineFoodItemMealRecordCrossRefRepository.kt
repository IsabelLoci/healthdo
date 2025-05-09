package com.example.calorietrackerapp.data.repository

import com.example.calorietrackerapp.data.dao.FoodItemMealRecordCrossRefDao
import com.example.calorietrackerapp.data.entity.FoodItemMealRecordCrossRef

class OfflineFoodItemMealRecordCrossRefRepository(private val dao: FoodItemMealRecordCrossRefDao) : FoodItemMealRecordCrossRefRepository {
    override suspend fun insertCrossRef(ref: FoodItemMealRecordCrossRef) = dao.insertCrossRef(ref)
}

