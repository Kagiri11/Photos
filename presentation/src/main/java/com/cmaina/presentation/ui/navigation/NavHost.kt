package com.cmaina.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmaina.presentation.screens.HomeScreen
import com.cmaina.presentation.screens.PhotoDetailsScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Destination.HomeScreen.route) {
        composable(route = Destination.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Destination.PhotoDetailScreen.route) {
            PhotoDetailsScreen()
        }
    }
}

sealed class Destination(val route: String) {
    object HomeScreen : Destination(route = "home_screen")
    object PhotoDetailScreen : Destination(route = "photo_detail_screen")
}
