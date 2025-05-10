package com.example.calorietrackerapp.ui.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorietrackerapp.data.entity.DailySummary
import com.example.calorietrackerapp.data.relation.MealRecordWithFoods
import com.example.calorietrackerapp.data.repository.DailySummaryRepository
import com.example.calorietrackerapp.data.repository.MealRecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

data class DailySummaryUiState(
    val date: LocalDate = LocalDate.now()
)

class DailySummaryViewModel(
    private val summaryRepo: DailySummaryRepository
) : ViewModel() {

    var summaryState = mutableStateOf<DailySummary?>(null)
        private set

    fun onDateSelected(date: LocalDate) {
        viewModelScope.launch {
            summaryRepo.getSummaryByDate(date).collect { summary ->
                summaryState.value = summary
            }
        }
    }
}
