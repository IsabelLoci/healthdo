package com.example.calorietrackerapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.calorietrackerapp.CalorieTrackerApplication
import com.example.calorietrackerapp.ui.viewmodel.MealSearchViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MealSearchViewModel(calorieTrackerApplicaton().container.foodItemRepository)
        }
    }
}

fun CreationExtras.calorieTrackerApplicaton(): CalorieTrackerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CalorieTrackerApplication)