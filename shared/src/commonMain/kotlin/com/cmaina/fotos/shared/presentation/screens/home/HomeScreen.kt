package com.cmaina.fotos.shared.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmaina.fotos.shared.presentation.utils.lazyItems
import com.cmaina.fotos.shared.presentation.components.photoscards.PhotoCardItem
import com.cmaina.presentation.components.photostext.FotosTitleText

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onPhotoClicked: (String) -> Unit
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    Column(modifier = Modifier.fillMaxSize()) {
        FotosTitleText(
            text = "Explore",
            textColor = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        )

        when (uiState) {
            is HomeUiState.Loading -> {}
            is HomeUiState.Error -> {}
            is HomeUiState.Success -> {
                val photos = uiState.pagedPhotos.collectAsLazyPagingItems()
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(1.dp),
                    state = lazyStaggeredGridState
                ) {
                    lazyItems(photos) { pic ->
                        PhotoCardItem(
                            blurHash = pic?.blurHash ?: "",
                            imageUrl = pic?.photoUrls?.small ?: "",
                            contentDescription = pic?.description ?: "",
                            onPhotoClicked = {
                                onPhotoClicked(pic?.id!!)
                            }
                        )
                    }
                }
            }
        }
    }
}
