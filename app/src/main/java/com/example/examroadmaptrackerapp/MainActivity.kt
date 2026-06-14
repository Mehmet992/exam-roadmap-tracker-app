package com.example.examroadmaptrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examroadmaptrackerapp.data.AppDatabase
import com.example.examroadmaptrackerapp.data.TrackerDAO
import com.example.examroadmaptrackerapp.data.TrackerRepository
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModelFactory
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModel
import com.example.examroadmaptrackerapp.ui.theme.ExamRoadmapTrackerAppTheme
import com.example.examroadmaptrackerapp.ui.theme.navigator.AppNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = TrackerRepository(database.trackerDao())

        val factory = TrackerViewModelFactory(repository)
        val viewModel: TrackerViewModel by viewModels { factory }

        setContent {
            ExamRoadmapTrackerAppTheme {
                AppNavigator(viewModel = viewModel)
            }
        }
    }
}
