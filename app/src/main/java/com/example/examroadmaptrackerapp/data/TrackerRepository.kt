package com.example.examroadmaptrackerapp.data

import kotlinx.coroutines.flow.Flow

class TrackerRepository(private val trackerDAO: TrackerDAO) {
    val allSubjects: Flow<List<SubjectEntity>> = trackerDAO.getAllSubjects()

    suspend fun insertSubject(subject : SubjectEntity) {
        trackerDAO.insertSubject(subject)
    }
}