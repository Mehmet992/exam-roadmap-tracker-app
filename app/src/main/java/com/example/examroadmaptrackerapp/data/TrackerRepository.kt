package com.example.examroadmaptrackerapp.data

import com.example.examroadmaptrackerapp.data.entities.SubjectEntity
import com.example.examroadmaptrackerapp.data.entities.TopicEntity
import com.example.examroadmaptrackerapp.data.entities.StudySessionEntity
import com.example.examroadmaptrackerapp.data.entities.AnalysisReportEntity
import com.example.examroadmaptrackerapp.data.entities.DailyLogEntity
import com.example.examroadmaptrackerapp.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

class TrackerRepository(private val trackerDAO: TrackerDAO) {
    val allSubjects: Flow<List<SubjectEntity>> = trackerDAO.getAllSubjects()

    suspend fun insertSubject(subject : SubjectEntity) {
        trackerDAO.insertSubject(subject)
    }

    suspend fun deleteSubject(subject: SubjectEntity) {
        trackerDAO.deleteSubject(subject)
    }

    suspend fun insertTopic(topic: TopicEntity) {
        trackerDAO.insertTopic(topic)
    }

    suspend fun deleteTopic(topic : TopicEntity) {
        trackerDAO.deleteTopic(topic)
    }

    suspend fun insertStudySession(session: StudySessionEntity) {
        trackerDAO.insertStudySession(session)
    }

    suspend fun deleteStudySession(session: StudySessionEntity) {
        trackerDAO.deleteStudySession(session)
    }

    suspend fun insertTask(task: TaskEntity) {
        trackerDAO.insertTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        trackerDAO.deleteTask(task)
    }

    suspend fun insertDailyLog(dailyLog: DailyLogEntity) {
        trackerDAO.insertDailyLog(dailyLog)
    }

    suspend fun insertAnalysisReport(analysisReport: AnalysisReportEntity) {
        trackerDAO.insertAnalysisReport(analysisReport)
    }

    fun getAllTopicsOfASubject(subjectId: Long): Flow<List<TopicEntity>> {
        return trackerDAO.getTopicsBySubject(subjectId)
    }
}