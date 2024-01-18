package com.cmaina.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cmaina.presentation.R
import com.cmaina.presentation.screens.favourites.FavouritesScreen
import com.cmaina.presentation.screens.home.HomeScreen
import com.cmaina.presentation.screens.home.HomeViewModel
import com.cmaina.presentation.screens.photodetails.PhotoDetailsScreen
import com.cmaina.presentation.screens.photodetails.PhotoDetailsViewModel
import com.cmaina.presentation.screens.search.SearchScreen
import com.cmaina.presentation.screens.settings.SettingsScreen
import com.cmaina.presentation.screens.user.UserScreen
import com.cmaina.presentation.screens.user.UserViewModel
import org.koin.androidx.compose.inject

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    val homeViewModel by inject<HomeViewModel>()
    val detailsViewModel by inject<PhotoDetailsViewModel>()
    val userViewModel by inject<UserViewModel>()

    NavHost(
        navController = navController,
        startDestination = Destination.HomeScreen.route,
        modifier = modifier
    ) {

        composable(route = Destination.HomeScreen.route) {
            val uiState by homeViewModel.homeState.collectAsStateWithLifecycle()
            HomeScreen(
                uiState = uiState,
                onPhotoClicked = { photoId ->
                    navController.navigate("photo_detail_screen/$photoId")
                }
            )
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
            val uiState by detailsViewModel.detailsUiState.collectAsStateWithLifecycle()
            val messageIsPresent by detailsViewModel.messageToUser.collectAsStateWithLifecycle()
            val userHasBeenAuthenticated by detailsViewModel.userIsAuthenticated.collectAsStateWithLifecycle()

            photoId?.let {
                PhotoDetailsScreen(
                    messageIsPresent = messageIsPresent,
                    uiState = uiState,
                    onInitialLoadEvent = { detailsViewModel.fetchPhoto(photoId) },
                    onUserSectionClickedEvent = { name -> navController.navigate("user_screen/$name") },
                    onImageLikedEvent = {
                        if (!userHasBeenAuthenticated) {
                            detailsViewModel.changeMessageStatus()
                        }
                        detailsViewModel.likePhoto(photoId)
                    },
                    onDialogDismissedEvent = { detailsViewModel.changeMessageStatus() },
                    onPageSwappedEvent = { detailsViewModel.checkIfPhotoHasBeenLiked(it) },
                    onUserRequestsAuthenticationEvent = { authCode ->
                        detailsViewModel.authenticateUser(authCode)
                    }
                )
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
            val userScreenUiState by userViewModel.uiState.collectAsStateWithLifecycle()
            val username = it.arguments?.getString("username")

            username?.let { name ->
                UserScreen(
                    uiState = userScreenUiState,
                    onScreenLoad = { userViewModel.fetchUser(name) },
                    onBackPressed = { navController.navigateUp() },
                    onUserPhotoClicked = { navController.navigate("photo_detail_screen/${it}") }
                )
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
