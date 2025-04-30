package com.example.calorietrackerapp

import FoodItem
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealSearchViewModel : ViewModel() {

    private val _searchResults = MutableStateFlow<List<FoodItem>>(emptyList())
    val searchResults: StateFlow<List<FoodItem>> = _searchResults

    fun searchMeal(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchFood(
                    appId = "907453eb",
                    appKey = "f947cf4ebefc97e6991e7660a3f3e039",
                    ingr = query
                )

                if (response.isSuccessful) {
                    val hints = response.body()?.hints ?: emptyList()
                    val foods = hints.map {
                        FoodItem(
                            name = it.food.label,
                            calories = it.food.nutrients.ENERC_KCAL
                        )
                    }
                    _searchResults.value = foods
                    Log.d("API_CALL", "Success! Items: $foods")
                } else {
                    _searchResults.value = emptyList()
                    Log.e("API_CALL", "Error: ${response.code()}")
                }

            } catch (e: Exception) {
                _searchResults.value = emptyList()
                Log.e("API_CALL", "Exception: ${e.message}")
            }
        }
    }
}
