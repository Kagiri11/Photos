package com.cmaina.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cmaina.presentation.screens.favourites.FavouritesScreen
import com.cmaina.presentation.screens.home.HomeScreen
import com.cmaina.presentation.screens.photodetails.PhotoDetailsScreen
import com.cmaina.presentation.screens.search.SearchScreen
import com.cmaina.presentation.screens.user.UserScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Destination.HomeScreen.route) {
        composable(route = Destination.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            Destination.PhotoDetailScreen.route,
            arguments = listOf(
                navArgument("photoID") {
                    type = NavType.StringType
                }
            )
        ) {
            val photoId = it.arguments?.getString("photoID")
            photoId?.let { id ->
                PhotoDetailsScreen(photoId = id)
            }
        }
        composable(
            route = Destination.UserScreen.route,
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                }
            )
        ) {
            val username = it.arguments?.getString("username")
            username?.let {
                UserScreen(it)
            }
        }
        composable(route = Destination.FavouritesScreen.route) {
            FavouritesScreen()
        }
        composable(route = Destination.SearchScreen.route) {
            SearchScreen()
        }
    }
}

sealed class Destination(
    val route: String,
    val label: String = "",
    @DrawableRes val icon: Int = 0
) {
    object HomeScreen : Destination(route = "home_screen", label = "Home")
    object PhotoDetailScreen : Destination(route = "photo_detail_screen/{photoID}")
    object UserScreen : Destination(route = "user_screen/{username}")
    object SearchScreen : Destination(route = "search_screen")
    object FavouritesScreen : Destination(route = "favourites_screen")
}
