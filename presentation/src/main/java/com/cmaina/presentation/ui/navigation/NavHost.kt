package com.cmaina.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cmaina.presentation.R
import com.cmaina.presentation.screens.favourites.FavouritesScreen
import com.cmaina.presentation.screens.home.HomeScreen
import com.cmaina.presentation.screens.photodetails.PhotoDetailsScreen
import com.cmaina.presentation.screens.search.SearchScreen
import com.cmaina.presentation.screens.settings.SettingsScreen
import com.cmaina.presentation.screens.user.UserScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.HomeScreen.route,
        modifier = modifier
    ) {
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
                PhotoDetailsScreen(photoId = id, navController = navController)
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
            username?.let { name ->
                UserScreen(username = name, navController = navController)
            }
        }
        composable(route = Destination.FavouritesScreen.route) {
            FavouritesScreen()
        }
        composable(route = Destination.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Destination.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}

sealed class Destination(
    val route: String,
    val label: String = "",
    @DrawableRes val icon: Int = 0
) {
    object HomeScreen :
        Destination(route = "home_screen", label = "Home", icon = R.drawable.ic_home)

    object PhotoDetailScreen : Destination(route = "photo_detail_screen/{photoID}")
    object UserScreen : Destination(route = "user_screen/{username}")
    object SearchScreen :
        Destination(route = "search_screen", label = "Search", icon = R.drawable.ic_search)

    object FavouritesScreen :
        Destination(
            route = "favourites_screen",
            label = "Favorites",
            icon = R.drawable.ic_favourite
        )

    object SettingsScreen :
        Destination(route = "settings_screen", label = "Settings", icon = R.drawable.ic_settings)
}
