package com.cmaina.fotos.shared.presentation.components.userscreencomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.cmaina.domain.models.photos.Photo
import com.cmaina.presentation.components.photoscards.AsyncImageBlur
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun UserPhotos(
    modifier: Modifier = Modifier,
    photos: LazyPagingItems<Photo>,
    onUserPhotoClicked: (String) -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(1.dp)
    ) {
        items(photos) { pic ->
            pic?.let {
                UserPhoto(
                    imageBlurHash = pic.blurHash ?: "",
                    userImageUrl = pic.photoUrls?.small ?: "",
                    contentDescription = pic.description ?: "",
                    onClick = { onUserPhotoClicked(pic.id) }
                )
            }
        }
    }
}

@Composable
fun UserPhoto(
    contentDescription: String,
    imageBlurHash: String,
    userImageUrl: String,
    onClick: () -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(0.5.dp)
            .placeholder(
                visible = false,
                highlight = PlaceholderHighlight.shimmer(highlightColor = FotosGreyShadeOneLightTheme),
                color = FotosGreyShadeThreeLightTheme
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(2),
        elevation = 0.dp
    ) {
        AsyncImageBlur(
            blurHash = imageBlurHash,
            imageUrl = userImageUrl,
            modifier = Modifier.fillMaxSize(),
            contentDescription = contentDescription
        )
    }
}
