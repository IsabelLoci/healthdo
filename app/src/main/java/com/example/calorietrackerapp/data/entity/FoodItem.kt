package com.example.calorietrackerapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodName : String,
    val kcal : Int,
    val imageUrl : String
)
