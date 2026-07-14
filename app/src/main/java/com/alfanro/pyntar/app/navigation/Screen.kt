package com.alfanro.pyntar.app.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth") // For PIN/Biometric auth later
    
    // Bottom Navigation Routes
    object Dashboard : Screen("dashboard")
    object TaskList : Screen("task_list")
    object Calendar : Screen("calendar")
    object Analytics : Screen("analytics")
    
    // Details / Nested
    object TaskDetail : Screen("task_detail/{taskId}") {
        fun createRoute(taskId: String) = "task_detail/$taskId"
    }
    
    object AddTask : Screen("add_task")
}
