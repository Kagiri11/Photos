package com.cmaina.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Settings screen", modifier = Modifier.align(Alignment.Center))
    }
}
