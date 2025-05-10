package com.example.calorietrackerapp.data.repository

import com.example.calorietrackerapp.data.entity.MealRecord
import com.example.calorietrackerapp.data.relation.MealRecordWithFoods
import kotlinx.coroutines.flow.Flow

interface MealRecordRepository {
    fun getMealsWithFoodsByDate(date: String): Flow<List<MealRecordWithFoods>?>
    fun getMealWithFoodsById(mealId: Int): Flow<MealRecordWithFoods>
    suspend fun insertMealRecord(meal: MealRecord): Long
    suspend fun updateMealRecord(meal: MealRecord)
    suspend fun deleteMealRecord(meal: MealRecord)
}