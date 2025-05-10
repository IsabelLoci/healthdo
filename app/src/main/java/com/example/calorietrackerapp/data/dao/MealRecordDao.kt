package com.example.calorietrackerapp.data.dao

import androidx.room.*
import com.example.calorietrackerapp.data.entity.MealRecord
import com.example.calorietrackerapp.data.relation.MealRecordWithFoods
import kotlinx.coroutines.flow.Flow

@Dao
interface MealRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealRecord(mealRecord: MealRecord): Long

    @Update
    suspend fun updateMealRecord(mealRecord: MealRecord)

    @Delete
    suspend fun deleteMealRecord(mealRecord: MealRecord)

    @Transaction
    @Query("SELECT * FROM meal_records WHERE id = :mealId")
    fun getMealWithFoods(mealId: Int): Flow<MealRecordWithFoods>

    @Transaction
    @Query("SELECT * FROM meal_records WHERE date = :date ORDER BY time ASC")
    fun getMealsByDate(date: String): Flow<List<MealRecordWithFoods>?>
}