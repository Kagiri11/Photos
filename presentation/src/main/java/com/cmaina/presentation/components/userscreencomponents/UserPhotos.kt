package com.cmaina.presentation.components.userscreencomponents

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.presentation.screens.items
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun UserPhotos(
    modifier: Modifier = Modifier,
    photos: LazyPagingItems<DomainPhotoListItem>?,
    navController: NavController
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(1.dp)
    ) {
        photos?.let {
            items(it) { pic ->
                UserPhoto(
                    userImageUrl = pic?.domainUrls?.small ?: "",
                    description = pic?.description ?: ""
                ) {
                    navController.navigate("photo_detail_screen/${pic?.id}")
                }
            }
        }
    }
}

@Composable
fun UserPhoto(userImageUrl: String, description: String, onClick: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .size(100.dp)
            .padding(0.5.dp)
            .placeholder(
                visible = false,
                highlight = PlaceholderHighlight.shimmer(highlightColor = FotosGreyShadeOneLightTheme),
                color = FotosGreyShadeThreeLightTheme
            ).clickable { onClick },
        shape = RoundedCornerShape(2),
        elevation = 0.dp
    ) {
        AsyncImage(
            model = userImageUrl,
            contentDescription = description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
