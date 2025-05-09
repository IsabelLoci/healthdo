package com.example.calorietrackerapp.data.repository

import com.example.calorietrackerapp.data.entity.DailySummary
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface DailySummaryRepository {
    fun getSummaryByDate(date: LocalDate): Flow<DailySummary>
    fun getAllSummaries(): Flow<List<DailySummary>>

    suspend fun insertSummary(summary: DailySummary)
    suspend fun updateSummary(summary: DailySummary)

}