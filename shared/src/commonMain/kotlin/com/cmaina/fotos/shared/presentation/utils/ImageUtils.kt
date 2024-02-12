package com.cmaina.fotos.shared.presentation.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.paging.compose.LazyPagingItems

@OptIn(ExperimentalFoundationApi::class)
inline fun <T : Any> LazyStaggeredGridScope.lazyItems(
    myItems: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyStaggeredGridItemScope.(value: T?) -> Unit
) {
    items(
        count = myItems.itemCount
    ) { index ->
        itemContent(myItems[index])
    }
}

inline fun <T : Any> LazyGridScope.myItems(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(value: T) -> Unit
) { items(count = items.itemCount) { index -> items[index]?.let { itemContent(it) } } }

/*
fun onResume(
    context: Context,
    authenticateUser: (String) -> Unit
) {

    val uri = context.findActivity()?.intent?.data
    val code = uri.toString().substringAfter("code=")
    authenticateUser(code)
}*/
