package com.example.examroadmaptrackerapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.examroadmaptrackerapp.data.SubjectEntity
import com.example.examroadmaptrackerapp.data.TrackerRepository

//uses TrackerRepository so that it can reach to database
class TrackerViewModel(private val repository: TrackerRepository): ViewModel() {
    val allSubjects = repository.allSubjects

    fun addSubject(subjectName: String) {
        viewModelScope.launch {
            val newSubject = SubjectEntity(name = subjectName)

            repository.insertSubject(newSubject)
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