package com.example.calorietrackerapp.data.repository


import com.example.calorietrackerapp.data.dao.FoodItemMealRecordCrossRefDao
import com.example.calorietrackerapp.data.entity.FoodItemMealRecordCrossRef

interface FoodItemMealRecordCrossRefRepository {
    suspend fun insertCrossRef(ref: FoodItemMealRecordCrossRef)
}