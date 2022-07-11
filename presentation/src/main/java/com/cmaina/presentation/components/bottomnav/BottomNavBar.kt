package com.cmaina.presentation.components.bottomnav

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.cmaina.presentation.ui.navigation.Destination

@Composable
fun RowScope.AddBottomNavItem(
    screen: Destination,
    currentScreen: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selected = currentScreen?.hierarchy?.any {
            it.route == screen.label
        } == true,
        onClick = {
            navController.navigate(screen.label) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        icon = {}
    )
}