package com.example.examroadmaptrackerapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["SubjectId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val topicId: Long? = null,
    val isDone: Boolean = false,
    val dueDateMillis: Long? = null
)
