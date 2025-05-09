package com.example.calorietrackerapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.calorietrackerapp.data.entity.DailySummary
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface DailySummaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSummary(summary: DailySummary)

    @Update
    suspend fun updateSummary(summary: DailySummary)

    @Query("SELECT * FROM daily_summaries WHERE date = :date")
    fun getSummaryByDate(date: LocalDate): Flow<DailySummary>

    @Query("SELECT * FROM daily_summaries ORDER BY date DESC")
    fun getAllSummaries(): Flow<List<DailySummary>>
}