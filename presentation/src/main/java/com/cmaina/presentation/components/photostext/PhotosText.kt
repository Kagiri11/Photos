package com.cmaina.presentation.components.photostext

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FotosText(text: String, textColor: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1.copy(color = textColor),
        modifier = modifier
    )
}

@Composable
fun FotosTitleText(text: String, textColor: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1.copy(color = textColor),
        modifier = modifier
    )
}

@Composable
fun FotosButtonText(text: String, textColor: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.button.copy(color = textColor),
        modifier = modifier
    )
}
