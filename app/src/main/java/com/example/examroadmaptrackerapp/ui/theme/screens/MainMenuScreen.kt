package com.example.examroadmaptrackerapp.ui.theme.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModel


// 1. Menü elemanlarımızı tanımladığımız sınıf (Route = Rotamız, Title = Ekranda yazan)
sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Menu : BottomNavItem("menu", "Menü", Icons.Default.Menu)
    object Subjects : BottomNavItem("subjects", "Dersler", Icons.Default.Book)
    object Study : BottomNavItem("study", "Çalışma", Icons.Default.Timer)
    object Profile : BottomNavItem("profile", "Hesap", Icons.Default.Person)
}

@Composable
fun MainMenuScreen(viewModel: TrackerViewModel, navController: NavController) {
    val bottomNavController = rememberNavController()

    // Menüde kullanacağımız sekmelerin listesi
    val items = listOf(
        BottomNavItem.Menu,
        BottomNavItem.Subjects,
        BottomNavItem.Study,
        BottomNavItem.Profile
    )

    // Scaffold: Ekranın ana çerçevesi (Alt barı buraya takıyoruz)
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val showBottomBar = currentRoute != BottomNavItem.Study.route

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    // Kullanıcının şu an hangi ekranda olduğunu anlık olarak dinliyoruz
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                            label = { Text(screen.title) },
                            // Eğer bulunduğumuz rota bu sekmenin rotasıysa, sekmeyi "seçili" (renkli) yap
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                bottomNavController.navigate(screen.route) {
                                    // Simplified navigation to avoid state saving issues for now
                                    popUpTo(bottomNavController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Menu.route, // Uygulama ilk açıldığında Dersler sekmesi gelsin
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Menu.route) {
                DashboardScreen(viewModel = viewModel, navController = bottomNavController)
            }
            composable(BottomNavItem.Subjects.route) {
                CoursesScreen(viewModel = viewModel, navController = navController)
            }

            composable(BottomNavItem.Study.route) {
                StudyScreen(
                    viewModel = viewModel,
                    navController = navController,
                    onBackToMenu = {
                        // Popping back from Study returns to the previous tab (usually Menu)
                        bottomNavController.popBackStack()
                    }
                )
            }
            composable(BottomNavItem.Profile.route) {
                // TODO: Hesap ve İstatistik ekranı buraya gelecek
                Text("Hesap ve Analiz Ekranı", modifier = Modifier.padding(16.dp))
            }
        }
    }
}