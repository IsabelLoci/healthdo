package com.example.calorietrackerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.calorietrackerapp.data.entity.FoodItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodItem: FoodItem)

    @Update
    suspend fun updateFood(foodItem: FoodItem)

    @Delete
    suspend fun deleteFood(foodItem: FoodItem)

    @Query("SELECT * FROM food_items ORDER BY foodName ASC")
    fun getAllFoodItems(): Flow<List<FoodItem>>

    @Query("SELECT * FROM food_items WHERE id = :id")
    fun getFoodItemById(id: Int): Flow<FoodItem>

    @Query("SELECT * FROM food_items WHERE foodName LIKE '%' || :search || '%'")
    fun searchFoodByName(search: String): Flow<List<FoodItem>>

}