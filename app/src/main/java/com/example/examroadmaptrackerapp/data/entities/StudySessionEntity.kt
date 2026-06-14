package com.example.examroadmaptrackerapp.data.entities

import com.example.examroadmaptrackerapp.enums.SessionTypes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey


@Entity(
    tableName = "study_sessions",
    foreignKeys = [
        ForeignKey(
            entity = TopicEntity::class,
            parentColumns = ["topicId"],
            childColumns = ["topicId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudySessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topicId: Long? = null, //This may become null because free time study
    val durationMinutes: Int,
    val dateMillis: Long,
    val sessionType: SessionTypes? = null
)
