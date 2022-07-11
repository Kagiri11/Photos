package com.cmaina.presentation.components.bottomnav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cmaina.presentation.ui.navigation.Destination
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosWhite

val TopLevelDestinations = listOf(
    Destination.HomeScreen,
    Destination.SearchScreen,
    Destination.FavouritesScreen,
    Destination.SettingsScreen
)

@Composable
fun FotosBottomNav(navHostController: NavHostController) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = FotosWhite) {
        TopLevelDestinations.forEach { screen ->
            AddBottomNavItem(
                screen = screen,
                currentScreen = currentScreen,
                navController = navHostController,
            )
        }
    }
}

@Composable
fun RowScope.AddBottomNavItem(
    screen: Destination,
    currentScreen: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        selected = currentScreen?.hierarchy?.any {
            it.route == screen.label
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        label = {
            Text(text = screen.label, style = TextStyle(color = FotosBlack))
        },
        alwaysShowLabel = true,
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.label,
                tint = Color.Black
            )
        },
    )
}
