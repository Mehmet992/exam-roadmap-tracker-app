package com.example.examroadmaptrackerapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examroadmaptrackerapp.data.entities.SubjectEntity
import com.example.examroadmaptrackerapp.data.entities.TopicEntity
import com.example.examroadmaptrackerapp.data.entities.TaskEntity
import com.example.examroadmaptrackerapp.data.entities.StudySessionEntity
import com.example.examroadmaptrackerapp.data.entities.DailyLogEntity
import com.example.examroadmaptrackerapp.data.entities.AnalysisReportEntity
import kotlinx.coroutines.flow.Flow

@Dao //Messenger file
interface TrackerDAO {
    //First operation: add a subject to table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject : SubjectEntity)

    @Delete
    suspend fun deleteSubject(subject : SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: TopicEntity)

    @Delete
    suspend fun deleteTopic(topic : TopicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudySession(studySession : StudySessionEntity)

    @Delete
    suspend fun deleteStudySession(studySession: StudySessionEntity)

    @Insert
    suspend fun insertDailyLog(dailyLogEntity: DailyLogEntity)

    @Insert
    suspend fun insertAnalysisReport(analysisReportEntity: AnalysisReportEntity)

    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM topics WHERE subjectId = :subjectId")
    fun getTopicsBySubject(subjectId: Long): Flow<List<TopicEntity>>


}