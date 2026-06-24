package com.example.examroadmaptrackerapp.ui.theme.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examroadmaptrackerapp.ui.theme.screens.TopicsScreen
import com.example.examroadmaptrackerapp.ui.theme.screens.LoginScreen
import com.example.examroadmaptrackerapp.ui.theme.screens.MainMenuScreen
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModel



@Composable
fun AppNavigator(viewModel: TrackerViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_menu" //this decides where your application will start at first
    ) {
        composable("main_menu") {
            MainMenuScreen(viewModel = viewModel, navController = navController)
        }

        composable("login_menu") {
            LoginScreen(viewModel = viewModel, navController = navController)
        }

        composable(
            route = "topics/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.LongType })
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getLong("subjectId") ?: 0L
            TopicsScreen(viewModel = viewModel, navController = navController, subjectId = subjectId)
        }
    }
}
