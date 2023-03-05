package com.cmaina.tacc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmaina.tacc.screens.details.DetailsScreen
import com.cmaina.tacc.screens.list.ListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(route = Screen.ListScreen.route) {
            ListScreen {
                navController.navigate(Screen.DetailScreen.route)
            }
        }
        composable(route = Screen.DetailScreen.route) {
            DetailsScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object ListScreen : Screen(route = "list")
    object DetailScreen : Screen(route = "detail")
}
