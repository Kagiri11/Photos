package com.cmaina.presentation.components.photoscards

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.lerp
import com.cmaina.presentation.screens.photodetails.PhotoLikedState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ColumnScope.PhotosPager(images: List<PhotoLikedState>, onPageSwapped: (String) -> Unit) {
    val res = LocalContext.current.resources
    HorizontalPager(
        count = images.size,
        modifier = Modifier.weight(0.7f).fillMaxWidth()
    ) { page ->
        onPageSwapped(images[page].photoId ?: "")
        Log.d("HelloThere", "This is the blurhash: ${images[page].blurHash}")
        Card(
            modifier = Modifier
                .fillMaxHeight(0.95f)
                .fillMaxWidth(0.95f)
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            shape = RoundedCornerShape(2)
        ) {
            AsyncImageBlur(
                blurHash = images[page].blurHash ?: "",
                imageUrl = images[page].photoUrl ?: "",
                resources = res,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
