package com.cmaina.tacc.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.getViewModel

@Composable
fun ListScreen(listViewModel: ListViewModel = getViewModel(), onItemClicked: () -> Unit) {
    val photos = listViewModel.photosOfMars.collectAsState().value
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(photos) {
                MarsPhotoItem(imageAndUrl = it.imgSrc) {
                    listViewModel.setPhotoToBeViewed(it)
                    onItemClicked()
                }
            }
        }
    }
}

@Composable
fun MarsPhotoItem(imageAndUrl: String, onClicked: () -> Unit) {
    val painter = rememberAsyncImagePainter(model = imageAndUrl)
    Image(
        painter = painter,
        contentDescription = "A picture of mars",
        modifier = Modifier.fillMaxWidth().height(200.dp).clickable { onClicked() },
        contentScale = ContentScale.Fit
    )
}
