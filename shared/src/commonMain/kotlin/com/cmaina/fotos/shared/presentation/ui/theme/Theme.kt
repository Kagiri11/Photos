package com.cmaina.fotos.shared.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember

private val DarkColorPalette = darkColors(
    primary = FotosBlack,
    primaryVariant = FotosBlack,
    secondary = FotosBlack,
    background = FotosBlack,
    onPrimary = FotosWhite,
    onSecondary = FotosWhite,
    onBackground = FotosWhite,
    onSurface = FotosWhite,

)

private val LightColorPalette = lightColors(
    primary = FotosWhite,
    primaryVariant = FotosBlack,
    secondary = FotosBlack,
    background = FotosWhite,
    surface = FotosWhite,
    onPrimary = FotosBlack,
    onSecondary = FotosBlack,
    onBackground = FotosBlack,
    onSurface = FotosBlack,
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
