package com.example.examroadmaptrackerapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "analysis_reports")
data class AnalysisReportEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dateGeneratedMillis: Long,
    val coveredPeriodStartMillis: Long,
    val coveredPeriodEndMillis: Long,
    val aiSummaryText: String
)
