package com.cmaina.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.paging.compose.LazyPagingItems
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder

inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(value: T) -> Unit
) {
    items(
        count = items.itemCount
    ) { index ->
        items[index]?.let { itemContent(it) }
    }
}

@OptIn(ExperimentalFoundationApi::class)
inline fun <T : Any> LazyStaggeredGridScope.lazyItems(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyStaggeredGridItemScope.(value: T?) -> Unit
) {
    items(
        count = items.itemCount
    ) { index ->
        itemContent(items[index])
    }
}

fun Modifier.myPlaceholder(shape: Shape) = this.placeholder(
    visible = false,
    color = FotosGreyShadeOneLightTheme,
    highlight = PlaceholderHighlight.fade(highlightColor = FotosGreyShadeThreeLightTheme),
    shape = shape
)
