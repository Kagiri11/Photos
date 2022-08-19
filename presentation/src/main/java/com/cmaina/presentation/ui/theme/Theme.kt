package com.cmaina.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = FotosBlack,
    primaryVariant = FotosBlack,
    secondary = FotosBlack,
    background = FotosBlack,
    onPrimary = FotosBlack,
    onSecondary = FotosBlack,
    onBackground = FotosBlack,
    onSurface = FotosWhite,

)

private val LightColorPalette = lightColors(
    primary = FotosWhite,
    primaryVariant = FotosBlack,
    secondary = FotosBlack,
    background = FotosWhite,
    surface = FotosWhite,
)

@Composable
fun FotosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
