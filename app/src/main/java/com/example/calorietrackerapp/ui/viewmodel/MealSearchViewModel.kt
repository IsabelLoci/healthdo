package com.example.calorietrackerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.data.repository.FoodItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MealSearchViewModel(private val foodItemRepository: FoodItemRepository) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<FoodItem>>(emptyList())
    val searchResults: StateFlow<List<FoodItem>> = _searchResults

    fun searchMeal(name: String) {
        viewModelScope.launch {
            viewModelScope.launch {
                foodItemRepository.searchFoodByName(name).collectLatest { result ->
                    _searchResults.value = result
                }
            }
        }
    }
}
