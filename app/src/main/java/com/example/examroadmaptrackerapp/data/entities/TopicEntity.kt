package com.example.examroadmaptrackerapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "topics",
    foreignKeys = [
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["subjectId"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class TopicEntity(
    @PrimaryKey(autoGenerate = true) val topicId: Long = 0,
    val subjectId: Long,
    val title: String,
    val isCompleted: Boolean = false,
    val completeDate: Long? = null
    )
