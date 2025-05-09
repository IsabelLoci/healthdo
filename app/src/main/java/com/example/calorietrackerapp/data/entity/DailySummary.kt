package com.example.calorietrackerapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "daily_summaries")
data class DailySummary(
    @PrimaryKey
    val date: LocalDate,
    val calorieConsumed: Int,
    val calorieBurned: Int,
    val netCalorie: Int,
    val healthStatus: Boolean
)