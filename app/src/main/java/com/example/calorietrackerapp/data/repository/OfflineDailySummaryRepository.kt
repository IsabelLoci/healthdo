package com.example.calorietrackerapp.data.repository

import com.example.calorietrackerapp.data.dao.DailySummaryDao
import com.example.calorietrackerapp.data.entity.DailySummary
import java.time.LocalDate

class OfflineDailySummaryRepository(
    private val dao: DailySummaryDao
) : DailySummaryRepository {
    override fun getSummaryByDate(date: LocalDate) = dao.getSummaryByDate(date)
    override fun getAllSummaries() = dao.getAllSummaries()
    override suspend fun insertSummary(summary: DailySummary) = dao.insertSummary(summary)
    override suspend fun updateSummary(summary: DailySummary) = dao.updateSummary(summary)
}