package com.example.examroadmaptrackerapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.examroadmaptrackerapp.data.entities.SubjectEntity
import com.example.examroadmaptrackerapp.data.TrackerRepository
import com.example.examroadmaptrackerapp.data.entities.TaskEntity
import com.example.examroadmaptrackerapp.data.entities.TopicEntity
import com.example.examroadmaptrackerapp.data.entities.StudySessionEntity
import com.example.examroadmaptrackerapp.data.entities.AnalysisReportEntity
import com.example.examroadmaptrackerapp.data.entities.DailyLogEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

//uses TrackerRepository so that it can reach to database
class TrackerViewModel(private val repository: TrackerRepository): ViewModel() {
    val allSubjects = repository.allSubjects.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun getTopicsForSubject(subjectId: Long) = repository.getAllTopicsOfASubject(subjectId)

    fun getSubjectById(subjectId: Long) = repository.getSubjectById(subjectId)

    //Creates a new subject named given subjectName and adds to database
    fun addSubject(subjectName: String, targetGrade: String? = null) {
        viewModelScope.launch {
            val newSubject = SubjectEntity(
                name = subjectName,
                targetGrade = targetGrade
            )

            repository.insertSubject(newSubject)
        }
    }

    fun deleteSubject(subjectEntity: SubjectEntity) {
        viewModelScope.launch {
            repository.deleteSubject(subjectEntity)
        }
    }

    //Creates the topic with the given title and adds it to the database
    fun addTopic(topicTitle: String, subjectId: Long) {
        viewModelScope.launch {
            val newTopicEntity = TopicEntity(
                subjectId = subjectId,
                title = topicTitle
            )

            repository.insertTopic(newTopicEntity)
        }
    }

    fun deleteTopic(topic: TopicEntity) {
        viewModelScope.launch {
            repository.deleteTopic(topic)
        }
    }

    fun updateTopic(topic: TopicEntity) {
        viewModelScope.launch {
            repository.insertTopic(topic) // REPLACE strategy handles update
        }
    }

    fun addTask(taskTitle: String, topicId: Long?) {
        viewModelScope.launch {
            val newTaskEntity = TaskEntity(
                title = taskTitle,
                topicId = topicId
            )

            repository.insertTask(newTaskEntity)
        }
    }

    fun deleteTask(taskEntity: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(taskEntity)
        }
    }

    fun addStudySession(topicId: Long, durationMinutes: Int, date: Long) {
        viewModelScope.launch {
            val newSession = StudySessionEntity(
                topicId = topicId,
                durationMinutes = durationMinutes,
                dateMillis = date
            )
            repository.insertStudySession(newSession)
        }
    }

    fun deleteStudySession(studySession: StudySessionEntity) {
        viewModelScope.launch {
            repository.deleteStudySession(studySession)
        }
    }

    fun addDailyLog(date: Long, note: String) {
        viewModelScope.launch {
            val newLog = DailyLogEntity(
                dateId = date,
                dailyLogText = note
            )
            repository.insertDailyLog(newLog)
        }
    }

    fun addAnalysisReport(reportContent: String, date: Long, start: Long, end: Long) {
        viewModelScope.launch {
            val newReport = AnalysisReportEntity(
                dateGeneratedMillis = date,
                coveredPeriodStartMillis = start,
                coveredPeriodEndMillis = end,
                aiSummaryText = reportContent
            )
            repository.insertAnalysisReport(newReport)
        }
    }

}

class TrackerViewModelFactory(private val repository: TrackerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrackerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}