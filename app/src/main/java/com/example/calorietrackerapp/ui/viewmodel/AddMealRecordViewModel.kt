package com.example.calorietrackerapp.ui.viewmodel

import android.content.IntentSender.OnFinished
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietrackerapp.data.entity.DailySummary
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.data.entity.FoodItemMealRecordCrossRef
import com.example.calorietrackerapp.data.entity.MealRecord
import com.example.calorietrackerapp.data.repository.DailySummaryRepository
import com.example.calorietrackerapp.data.repository.FoodItemMealRecordCrossRefRepository
import com.example.calorietrackerapp.data.repository.MealRecordRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class MealRecordUiState(
    val time: String = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")),
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
) {
    val isValid: Boolean
        get() = time.isNotBlank()
}

class AddMealRecordViewModel(
    private val mealRepo: MealRecordRepository,
    private val summaryRepo: DailySummaryRepository,
    private val crossRefRepo: FoodItemMealRecordCrossRefRepository
) : ViewModel() {

    private val _selectedFoodItems = mutableStateListOf<FoodItem>()
    val selectedFoodItems: List<FoodItem> get() = _selectedFoodItems

    var uiState by mutableStateOf(MealRecordUiState())
        private set

    fun updateUiState(newState: MealRecordUiState) {
        uiState = newState
    }


    // allow user to select and cancel the food chosen
    fun toggleFoodItem(foodItem: FoodItem) {
        if (_selectedFoodItems.contains(foodItem)) {
            _selectedFoodItems.remove(foodItem)
        } else {
            _selectedFoodItems.add(foodItem)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveMeal(onFinished: () -> Unit = {} ){
        // if no food selected then don't do anything
        if (_selectedFoodItems.isEmpty()) return

        viewModelScope.launch {
            //Convert string to date and time with correct format
            val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

            val currentDate = LocalDate.parse(uiState.date, dateFormatter)
            val currentTime = LocalTime.parse(uiState.time, timeFormatter)
            val totalKcal = _selectedFoodItems.sumOf { it.kcal }

            // insert meal record into the db
            val meal = MealRecord(date = currentDate, time = currentTime, totalKcal = totalKcal)
            val mealId = mealRepo.insertMealRecord(meal).toInt()

            // Insert the food items into the cross refs
            _selectedFoodItems.forEach{ foodItem ->
                crossRefRepo.insertCrossRef(
                    FoodItemMealRecordCrossRef(
                        foodId = foodItem.id,
                        mealId = mealId,
                        quantity = 1 // TODO: default to 1, might change in the future
                    )
                )
            }

            // Update or insert into the daily summary
            val existingSummary = summaryRepo.getSummaryByDate(currentDate).firstOrNull()
            if (existingSummary != null){
                val updated = existingSummary.copy(
                    calorieConsumed = existingSummary.calorieConsumed + totalKcal,
                    netCalorie = existingSummary.netCalorie + totalKcal
                )
                summaryRepo.updateSummary(updated)
            }
            else{
                summaryRepo.insertSummary(
                    DailySummary(
                        date = currentDate,
                        calorieConsumed = totalKcal,
                        calorieBurned = 0,
                        netCalorie = totalKcal,
                        healthStatus = true // TODO health status default to true, but in future, if over a user's setting amount then false
                    )
                )
            }

            onFinished()

        }

    }
}

