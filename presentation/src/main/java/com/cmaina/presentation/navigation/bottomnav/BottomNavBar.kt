package com.cmaina.presentation.navigation.bottomnav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cmaina.presentation.navigation.Destination
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosGreyShadeTwoLightTheme

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

    BottomNavigation(modifier = Modifier.fillMaxWidth(), backgroundColor = MaterialTheme.colors.primary) {
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
    val isSelected = currentScreen?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        label = {
            Text(
                text = screen.label,
                style = TextStyle(color = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary)
            )
        },
        alwaysShowLabel = isSelected,
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.label,
                tint = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onPrimary
            )
        },
    )
}
