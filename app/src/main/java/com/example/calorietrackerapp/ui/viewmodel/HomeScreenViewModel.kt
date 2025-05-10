package com.example.calorietrackerapp.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietrackerapp.data.entity.DailySummary
import com.example.calorietrackerapp.data.entity.MealRecord
import com.example.calorietrackerapp.data.relation.MealRecordWithFoods
import com.example.calorietrackerapp.data.repository.DailySummaryRepository
import com.example.calorietrackerapp.data.repository.FoodItemMealRecordCrossRefRepository
import com.example.calorietrackerapp.data.repository.MealRecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun emptySummaryForToday(): DailySummary {
    val today = LocalDate.now()
    return DailySummary(
        date = today,
        calorieConsumed = 0,
        calorieBurned = 0,
        netCalorie = 0,
        healthStatus = true
    )
}

class HomeScreenViewModel(private val mealRepo: MealRecordRepository,
                          private val summaryRepo: DailySummaryRepository,
) : ViewModel() {

    // Fetch for today's calorie summary
    @RequiresApi(Build.VERSION_CODES.O)
    val today: LocalDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val todaySummary: StateFlow<DailySummary> = summaryRepo.getSummaryByDate(today)
        .map { it ?: emptySummaryForToday() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptySummaryForToday()
        )


    // meal record of today loaded from the mealRepo based on today's date and order by the time early to late time
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayMeals(): Flow<List<MealRecordWithFoods>?> {
        val today = LocalDate.now()
        return mealRepo.getMealsWithFoodsByDate(today.toString())
    }


}