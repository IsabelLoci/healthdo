package com.example.calorietrackerapp.data.dao

import androidx.room.*
import com.example.calorietrackerapp.data.entity.FoodItemMealRecordCrossRef

@Dao
interface FoodItemMealRecordCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: FoodItemMealRecordCrossRef)

    @Delete
    suspend fun deleteCrossRef(crossRef: FoodItemMealRecordCrossRef)
}
