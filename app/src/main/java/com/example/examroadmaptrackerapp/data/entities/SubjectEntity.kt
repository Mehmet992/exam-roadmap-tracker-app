package com.example.examroadmaptrackerapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//Create a database table named Subjects within this class
@Entity(
    tableName = "subjects"
)

data class SubjectEntity (
    //PrimaryKey automatically generates unique ids for every subject, starts from 0, 1, 2, 3, 4 ...
    @PrimaryKey(autoGenerate = true) val subjectId: Long = 0,
    val name: String,
    val targetGrade: String? = null
)
