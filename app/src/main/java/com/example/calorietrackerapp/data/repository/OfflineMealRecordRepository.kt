package com.example.calorietrackerapp.data.repository


import com.example.calorietrackerapp.data.dao.MealRecordDao
import com.example.calorietrackerapp.data.entity.MealRecord

class OfflineMealRecordRepository(
    private val dao: MealRecordDao
) : MealRecordRepository {
    override fun getMealsByDate(date: String) = dao.getMealsByDate(date)
    override fun getMealWithFoods(mealId: Int) = dao.getMealWithFoods(mealId)
    override suspend fun insertMealRecord(meal: MealRecord) = dao.insertMealRecord(meal)
    override suspend fun updateMealRecord(meal: MealRecord) = dao.updateMealRecord(meal)
    override suspend fun deleteMealRecord(meal: MealRecord) = dao.deleteMealRecord(meal)
}