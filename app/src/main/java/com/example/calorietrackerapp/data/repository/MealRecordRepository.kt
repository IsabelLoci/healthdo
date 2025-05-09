package com.example.calorietrackerapp.data.repository

import com.example.calorietrackerapp.data.entity.MealRecord
import com.example.calorietrackerapp.data.relation.MealRecordWithFoods
import kotlinx.coroutines.flow.Flow

interface MealRecordRepository {
    fun getMealsByDate(date: String): Flow<List<MealRecord>>
    fun getMealWithFoods(mealId: Int): Flow<MealRecordWithFoods>
    suspend fun insertMealRecord(meal: MealRecord): Long
    suspend fun updateMealRecord(meal: MealRecord)
    suspend fun deleteMealRecord(meal: MealRecord)
}