package com.alfanro.pyntar.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PyntarNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Dashboard.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Dashboard.route) {
            PlaceholderScreen(title = "Dashboard Screen")
        }
        
        composable(Screen.TaskList.route) {
            PlaceholderScreen(title = "Task List Screen")
        }
        
        composable(Screen.Calendar.route) {
            PlaceholderScreen(title = "Calendar Screen")
        }
        
        composable(Screen.Analytics.route) {
            PlaceholderScreen(title = "Analytics Screen")
        }
        
        composable(Screen.Auth.route) {
            PlaceholderScreen(title = "Authentication Screen")
        }
    }
}

// Temporary placeholder for features that will be built in subsequent sprints
@Composable
fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
