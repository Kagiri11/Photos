package com.cmaina.presentation.components.photostext

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PhotosText(text: String, textColor: Color, modifier: Modifier = Modifier) {
    Text(text = text, style = MaterialTheme.typography.body1.copy(color = textColor), modifier = modifier)
}

@Composable
fun PhotosTitleText(text: String, textColor: Color) {
    Text(text = text, style = MaterialTheme.typography.h1.copy(color = textColor))
}

@Composable
fun PhotosButtonText(text: String, textColor: Color) {
    Text(text = text, style = MaterialTheme.typography.button.copy(color = textColor))
}
