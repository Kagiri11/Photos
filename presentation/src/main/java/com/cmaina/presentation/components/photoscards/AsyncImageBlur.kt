package com.cmaina.presentation.components.photoscards

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cmaina.presentation.materials.BlurHashDecoder
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AsyncImageBlur(
    modifier: Modifier = Modifier,
    blurHash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 700,
    resources: Resources,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val bitmap = BlurHashDecoder.decode(blurHash, 4, 3)
    val bitmapDrawable = BitmapDrawable(resources, bitmap)
    val requestBuilder = Glide.with(LocalView.current).asDrawable().load(imageUrl).placeholder(bitmapDrawable).transition(
        DrawableTransitionOptions.withCrossFade(crossFadeAnimDuration))
    GlideImage(
        imageModel = imageUrl,
        requestBuilder = { requestBuilder },
        modifier = modifier, placeHolder = bitmap?.asImageBitmap(),
        contentScale = contentScale
    )
}