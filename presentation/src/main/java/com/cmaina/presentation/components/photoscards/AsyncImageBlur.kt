package com.cmaina.presentation.components.photoscards

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import com.cmaina.presentation.materials.BlurHashDecoder
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.skydoves.landscapist.fresco.FrescoImage

@Composable
fun AsyncImageBlur(
    modifier: Modifier = Modifier,
    blurHash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 400,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val resources = LocalContext.current.resources
    val bitmap = BlurHashDecoder.decode(blurHash, 4, 3)
    val bitmapDrawable = BitmapDrawable(resources, bitmap)
    val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
        .setLocalThumbnailPreviewsEnabled(true)
        .setProgressiveRenderingEnabled(true)
        .build()
    val imagePainter = rememberImagePainter(data = imageUrl) {
        crossfade(crossFadeAnimDuration)
        placeholder(bitmapDrawable)
    }
    FrescoImage(
        imageUrl = imageUrl,
        imageRequest = { imageRequest },
        contentScale = contentScale,
        modifier = modifier,
        placeHolder = imagePainter
    )
}
