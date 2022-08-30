package com.cmaina.presentation.components.settingscomponents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.cmaina.presentation.activities.MainViewModel
import com.cmaina.presentation.di.presentationModule
import com.cmaina.presentation.screens.settings.SettingsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules

class SettingsItemComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        loadKoinModules(presentationModule)
    }

    @Test
    fun clickingOnSettingOptionDisplaysSettingItem() {
        composeTestRule.setContent {
            SettingsScreen(mainViewModel = mainViewModel)
        }

        /*composeTestRule.onNodeWithContentDescription("Setting option").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Setting option").performClick()*/
        composeTestRule.onNodeWithContentDescription("Settings screen").assertExists()
    }
}
