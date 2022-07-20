package com.cmaina.presentation.components.photoscards

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PhotoCardItem(imageUrl: String?, username: String, navController: NavController) {
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.9f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutLinearInEasing)
        )
    )
    val linearGradient = Brush.linearGradient(
        colors = colors,
        start = Offset(200f, 200f),
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    val request = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true).build()
    Card(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(bottom = 5.dp, start = 2.dp, end = 3.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        AsyncImage(
            model = request,
            contentDescription = "image flani",
            modifier = Modifier.fillMaxSize().clickable {
                navController.navigate("user_screen/$username")
            }.background(brush = linearGradient),
            contentScale = ContentScale.Crop,
        )
    }
}
