package com.cmaina.presentation.components.settingscomponents

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.cmaina.presentation.R
import org.junit.Rule
import org.junit.Test

class SettingsItemComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingOnSettingOptionDisplaysSettingItem() {
        composeTestRule.setContent {
            Setting(settingName = "Display", settingAttribute = "Theme", attributeValue = "dark", settingIcon = R.drawable.ic_settings) {
            }
        }

        composeTestRule.onNodeWithContentDescription("Setting option").performClick()
        composeTestRule.onNodeWithContentDescription("Setting dialog").assertIsDisplayed()
    }
}
