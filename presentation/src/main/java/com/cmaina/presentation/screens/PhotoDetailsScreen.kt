package com.cmaina.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cmaina.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(homeViewModel: HomeViewModel = getViewModel()) {
    val photo = homeViewModel.randomPhoto.value
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .crossfade(true)
            .data(photo?.domainUrls?.regular)
            .build()
        ,
        contentDescription = photo?.description,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

