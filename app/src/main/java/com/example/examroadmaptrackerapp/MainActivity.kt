package com.example.examroadmaptrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examroadmaptrackerapp.data.AppDatabase
import com.example.examroadmaptrackerapp.data.TrackerRepository
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModelFactory
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModel
import com.example.examroadmaptrackerapp.ui.theme.ExamRoadmapTrackerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Database -> Repository -> Factory bridge
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = TrackerRepository(database.trackerDao())
        val factory = TrackerViewModelFactory(repository)

        setContent {
            ExamRoadmapTrackerAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrackerScreen(factory)
                }
            }
        }
    }
}

@Composable
fun TrackerScreen(factory: TrackerViewModelFactory) {
    val viewModel: TrackerViewModel = viewModel(factory = factory)

    val subjects by viewModel.allSubjects.collectAsState(initial = emptyList())

    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Add a new exam or course", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it},
                label =  { Text("e.g. , YKS, Physics 1")},
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.addSubject(inputText)
                        inputText = ""
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Save")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //Display Section
        Text(text = "My Tracked Subjects", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(subjects) { subject ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = subject.name,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
