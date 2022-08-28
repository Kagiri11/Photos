package com.cmaina.presentation.components.photoscards

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.cmaina.presentation.materials.BlurHashDecoder

@Composable
fun AsyncImageBlur(
    modifier: Modifier = Modifier,
    blurHash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 400,
    resources: Resources,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val bitmap = BlurHashDecoder.decode(blurHash, 4, 3)
    val bitmapDrawable = BitmapDrawable(resources, bitmap)
    val imagePainter = rememberImagePainter(data = imageUrl) {
        crossfade(crossFadeAnimDuration)
        placeholder(bitmapDrawable)
    }
    Image(
        painter = imagePainter,
        contentDescription = contentDescription ?: "image",
        modifier = modifier,
        contentScale = contentScale
    )
}
