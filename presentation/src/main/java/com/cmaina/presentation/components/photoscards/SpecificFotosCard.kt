package com.cmaina.presentation.components.photoscards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.cmaina.presentation.screens.photodetails.PhotoLikedState
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnScope.PhotosPager(
    images: List<PhotoLikedState>,
    pageInIteration: (Int) -> Unit,
    onPageSwapped: (String) -> Unit
) {
    val pagerState = rememberPagerState()
    HorizontalPager(
        pageCount = images.size,
        modifier = Modifier
            .weight(0.7f)
            .fillMaxWidth(),
        state = pagerState
    ) { page ->
        pageInIteration(pagerState.currentPage)
        onPageSwapped(images[page].photoId ?: "")
        Card(
            modifier = Modifier
                .fillMaxHeight(0.95f)
                .fillMaxWidth(0.95f)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                },
            shape = RoundedCornerShape(2)
        ) {
            AsyncImageBlur(
                blurHash = images[page].blurHash ?: "",
                imageUrl = images[page].photoUrl ?: "",
                modifier = Modifier.fillMaxSize(),
                contentDescription = ""
            )
        }
    }
}
