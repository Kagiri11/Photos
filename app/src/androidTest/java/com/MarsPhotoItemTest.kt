package com

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
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
    fun actionIsPerformedWhenMarsItemIsClicked() {
        var hasBeenClicked = false
        val imageAndUrl = ""

        composeTestRule.setContent {
            MarsPhotoItem(imageAndUrl = imageAndUrl, onClicked = { hasBeenClicked = true })
        }

        composeTestRule.onNode(hasClickAction()).performClick()

        assertTrue(hasBeenClicked)
    }
}
