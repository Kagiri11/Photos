package com.cmaina.tacc.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.getViewModel

@Composable
fun ListScreen(listViewModel: ListViewModel = getViewModel()) {
    val photos = listViewModel.photosOfMars.collectAsState().value
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(photos) {
                MarsPhotoItem(imageAndUrl = it.imgSrc to it.id)
            }
        }
    }
}

@Composable
fun MarsPhotoItem(imageAndUrl: Pair<String, Int>) {
    val painter = rememberAsyncImagePainter(model = imageAndUrl.first)
    Image(
        painter = painter,
        contentDescription = "A picture of mars",
        modifier = Modifier.size(100.dp).clip(RoundedCornerShape(20.dp))
    )
}
