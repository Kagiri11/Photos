package com.cmaina.presentation.components.photostext

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme
import com.google.accompanist.placeholder.placeholder

@Composable
fun FotosText(modifier: Modifier = Modifier, text: String, textColor: Color = MaterialTheme.colors.onPrimary, fontSize: Int = 16) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1.copy(color = textColor, fontSize = fontSize.sp),
        modifier = modifier.placeholder(visible = false, color = FotosGreyShadeThreeLightTheme, shape = RoundedCornerShape(50))
    )
}

@Composable
fun FotosTitleText(text: String, textColor: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1.copy(color = textColor),
        modifier = modifier.placeholder(visible = false, color = FotosGreyShadeThreeLightTheme, shape = RoundedCornerShape(50))
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
