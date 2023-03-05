package com

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cmaina.tacc.screens.list.MarsPhotoItem
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class MarsPhotoItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun marsPhotoItem_onClick() {
        var clicked = false
        val onClicked = { clicked = true }
        val imageAndUrl = "https://some.url/to/mars/photo.jpg"

        composeTestRule.setContent {
            MarsPhotoItem(imageAndUrl = imageAndUrl, onClicked = onClicked)
        }

        // Click on the Image composable
        composeTestRule.onNode(hasClickAction()).performClick()

        // Check that onClick is triggered
        assertTrue(clicked)
    }

    @Test
    fun marsPhotoItem_contentDescription() {
        val imageAndUrl = "https://some.url/to/mars/photo.jpg"

        composeTestRule.setContent {
            MarsPhotoItem(imageAndUrl = imageAndUrl, onClicked = {})
        }

        // Check the content description of the Image composable
        composeTestRule.onNodeWithContentDescription("A picture of mars")
            .assertIsDisplayed()
    }
}
