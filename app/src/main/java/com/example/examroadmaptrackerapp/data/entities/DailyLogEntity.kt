package com.example.examroadmaptrackerapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "daily_logs"
)
data class DailyLogEntity(
    @PrimaryKey val dateId: Long,
    val dailyLogText: String
)
