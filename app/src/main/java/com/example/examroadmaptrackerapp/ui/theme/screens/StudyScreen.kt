package com.example.examroadmaptrackerapp.ui.theme.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModel

sealed class StudyNavItem(val route: String, val title: String, val icon: ImageVector) {

    object StudySummary : StudyNavItem("study_sum", "Çalışma Özeti", Icons.Default.Menu)
    object Free : StudyNavItem("free_study", "Serbest", Icons.Default.Timer)
    object Pomodoro : StudyNavItem("pomodoro_study", "Pomodoro", Icons.Default.HourglassEmpty)
    object OneHour : StudyNavItem("one_hour_study", "1 Saat", Icons.Default.Schedule)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyScreen(
    viewModel: TrackerViewModel,
    navController: NavController,
    onBackToMenu: () -> Unit
) {
    val studyNavController = rememberNavController()
    val items = listOf(
        StudyNavItem.StudySummary,
        StudyNavItem.Free,
        StudyNavItem.Pomodoro,
        StudyNavItem.OneHour
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Çalışma") },
                navigationIcon = {
                    IconButton(onClick = onBackToMenu) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by studyNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            studyNavController.navigate(screen.route) {
                                popUpTo(studyNavController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = studyNavController,
            startDestination = StudyNavItem.Free.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(StudyNavItem.StudySummary.route) {
                Text("Ders Çalışma Özeti", modifier = Modifier.padding(16.dp))
            }

            composable(StudyNavItem.Free.route) {
                Text("Serbest Çalışma Ekranı", modifier = Modifier.padding(16.dp))
            }
            composable(StudyNavItem.Pomodoro.route) {
                Text("Pomodoro Çalışma Ekranı", modifier = Modifier.padding(16.dp))
            }
            composable(StudyNavItem.OneHour.route) {
                Text("1 Saatlik Çalışma Ekranı", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
