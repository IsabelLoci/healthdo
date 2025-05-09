package com.example.calorietrackerapp.data.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.data.entity.FoodItemMealRecordCrossRef
import com.example.calorietrackerapp.data.entity.MealRecord

data class MealRecordWithFoods(
    @Embedded val mealRecord: MealRecord,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = FoodItemMealRecordCrossRef::class,
            parentColumn = "mealId",
            entityColumn = "foodId"
        )
    )
    val foods: List<FoodItem>
    )
