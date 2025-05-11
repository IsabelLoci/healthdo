package com.example.calorietrackerapp.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.calorietrackerapp.data.relation.MealRecordWithFoods
import com.example.calorietrackerapp.data.repository.MealRecordRepository
import kotlinx.coroutines.flow.Flow

class SummaryDetailViewModel(
    private val mealRepo: MealRecordRepository,
) : ViewModel() {

    // meal record of selected date loaded from the mealRepo and order by the time early to late time
    @RequiresApi(Build.VERSION_CODES.O)
    fun getSelectedDateMeals(date: kotlinx.datetime.LocalDate): Flow<List<MealRecordWithFoods>?> {
        return mealRepo.getMealsWithFoodsByDate(date.toString())
    }


}