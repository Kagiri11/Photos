package com.cmaina.presentation.components.settingscomponents

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.cmaina.presentation.R
import org.junit.Rule
import org.junit.Test

class SettingsItemComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickingOnSettingOptionDisplaysSettingItem() {

        composeTestRule.setContent {
            Setting(
                settingName = "Display",
                settingAttribute = "Theme",
                attributeValue = "Dark",
                settingIcon = R.drawable.ic_dark_mode
            ) {
            }
        }
        composeTestRule.onNodeWithContentDescription("setting column").assertExists()
    }
}
