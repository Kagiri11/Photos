package com.cmaina.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cmaina.presentation.R

// To be moved to materials
val appFonts = FontFamily(
    Font(R.font.mulish_bold, weight = FontWeight.Bold),
    Font(R.font.mulish_regular, weight = FontWeight.Normal),
    Font(R.font.mulish_medium, weight = FontWeight.Medium),
    Font(R.font.mulish_semibold, weight = FontWeight.SemiBold)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = appFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    h1 = TextStyle(
        fontFamily = appFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = appFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    button = TextStyle(
        fontFamily = appFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)
