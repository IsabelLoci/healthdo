package com.example.calorietrackerapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.calorietrackerapp.CalorieTrackerApplication
import com.example.calorietrackerapp.ui.screens.AddNewMealRecord
import com.example.calorietrackerapp.ui.viewmodel.AddMealRecordViewModel
import com.example.calorietrackerapp.ui.viewmodel.DailySummaryViewModel
import com.example.calorietrackerapp.ui.viewmodel.HomeScreenViewModel
import com.example.calorietrackerapp.ui.viewmodel.MealSearchViewModel
import com.example.calorietrackerapp.ui.viewmodel.SummaryDetailViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MealSearchViewModel(calorieTrackerApplicaton().container.foodItemRepository)
        }

        initializer {
            AddMealRecordViewModel(
                mealRepo = calorieTrackerApplicaton().container.mealRecordRepository,
                summaryRepo = calorieTrackerApplicaton().container.dailySummaryRepository,
                crossRefRepo = calorieTrackerApplicaton().container.foodItemMealRecordCrossRefRepository
            )
        }

        initializer {
            HomeScreenViewModel(
                mealRepo = calorieTrackerApplicaton().container.mealRecordRepository,
                summaryRepo = calorieTrackerApplicaton().container.dailySummaryRepository,
            )
        }

        initializer {
            DailySummaryViewModel(
                summaryRepo = calorieTrackerApplicaton().container.dailySummaryRepository,
            )
        }

        initializer {
            SummaryDetailViewModel(
                mealRepo = calorieTrackerApplicaton().container.mealRecordRepository,
            )
        }
    }
}

fun CreationExtras.calorieTrackerApplicaton(): CalorieTrackerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CalorieTrackerApplication)