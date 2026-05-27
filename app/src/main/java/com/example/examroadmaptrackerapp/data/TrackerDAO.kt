package com.example.examroadmaptrackerapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao //Messenger file
interface TrackerDAO {
    //First operation: add a subject to table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject : SubjectEntity)

    @Query("SELECT * FROM Subjects")
    fun getAllSubjects(): Flow<List<SubjectEntity>>
}