package com.cmaina.fotos.shared.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.cmaina.fotos.shared.presentation.screens.home.HomeScreen
import com.cmaina.fotos.shared.presentation.screens.home.HomeViewModel
import com.cmaina.fotos.shared.presentation.screens.photodetails.PhotoDetailsScreen
import com.cmaina.fotos.shared.presentation.screens.photodetails.PhotoDetailsViewModel
import com.cmaina.fotos.shared.presentation.screens.user.UserScreen
import com.cmaina.fotos.shared.presentation.screens.user.UserViewModel
import com.cmaina.fotos.shared.presentation.utils.AppContext
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun FotosNavHost() {
    val navigator = rememberNavigator()

    // view models
    val homeViewModel = koinViewModel(HomeViewModel::class)
    val userViewModel = koinViewModel(UserViewModel::class)
    val photoDetailsViewModel = koinViewModel(PhotoDetailsViewModel::class)

    // ui states
    val photoDetailsScreenUiState = photoDetailsViewModel.uiState.collectAsState().value
    val homeScreenUiState = homeViewModel.uiState.collectAsState().value
    val userDetailsScreenUiState = userViewModel.uiState.collectAsState().value

    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = ""
    ) {

        scene("/home", navTransition = NavTransition()) {
            HomeScreen(
                uiState = homeScreenUiState,
                onPhotoClicked = {}
            )
        }

        scene("/photo_details", navTransition = NavTransition()) {
            PhotoDetailsScreen(
                onDialogDismissedEvent = {},
                onInitialLoadEvent = {},
                onUserSectionClickedEvent = {},
                onImageLikedEvent = {},
                onPageSwappedEvent = {},
                messageIsPresent = false,
                onUserRequestsAuthenticationEvent = {},
                uiState = photoDetailsScreenUiState,
                context = AppContext()
            )
        }

        scene("/user_screen", navTransition = NavTransition()) {
            UserScreen(
                onScreenLoad = {},
                onBackPressed = {},
                onUserPhotoClicked = {},
                uiState = userDetailsScreenUiState
            )
        }
    }
}