package com.cmaina.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.ui.theme.FotosBlack

@Composable
fun MainScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        FotosText(
            "Hello Success",
            textColor = FotosBlack,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}
