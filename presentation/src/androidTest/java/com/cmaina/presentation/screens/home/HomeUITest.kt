package com.cmaina.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmaina.presentation.di.KoinTestRule
import com.cmaina.presentation.di.presentationModule
import com.cmaina.presentation.navigation.NavGraph
import com.cmaina.presentation.navigation.bottomnav.FotosBottomNav
import com.cmaina.presentation.navigation.bottomnav.TopLevelDestinations
import com.cmaina.presentation.ui.theme.FotosTheme
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.unloadKoinModules

class HomeUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(presentationModule)
    )

    @Test
    fun homeScreenIsDisplayed() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val isTopLevelDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route in TopLevelDestinations.map { it.route }

            FotosTheme {
                Scaffold(
                    bottomBar = {
                        if (isTopLevelDestination) {
                            FotosBottomNav(navHostController = navController)
                        }
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
                ) {
                    NavGraph(navController = navController, modifier = Modifier.padding(it))
                }
            }
        }
        composeTestRule.onNodeWithText("Explore").assertIsDisplayed()
    }

    @After
    fun teardown() {
        unloadKoinModules(presentationModule)
    }
}
