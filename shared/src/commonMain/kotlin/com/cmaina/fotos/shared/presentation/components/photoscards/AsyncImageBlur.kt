package com.cmaina.fotos.shared.presentation.components.photoscards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun AsyncImageBlur(
    modifier: Modifier = Modifier,
    blurHash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 400,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Crop
) {
    KamelImage(
        resource = asyncPainterResource(imageUrl),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
    )
}
