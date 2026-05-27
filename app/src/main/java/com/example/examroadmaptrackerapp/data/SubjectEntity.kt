package com.example.examroadmaptrackerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Create a database table named Subjects within this class
@Entity(tableName = "Subjects")
data class SubjectEntity (
    //PrimaryKey automatically generates unique ids for every subject, starts from 0, 1, 2, 3, 4 ...
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    //Every subject has its own name: String
    val name: String
)