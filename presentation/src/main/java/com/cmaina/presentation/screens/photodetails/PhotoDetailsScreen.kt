package com.cmaina.presentation.screens.photodetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.cmaina.presentation.components.photoscards.SpecificFotoCard
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(homeViewModel: PhotoDetailsViewModel = getViewModel(), photoId: String) {
    SideEffect {
        homeViewModel.fetchPhoto(photoId)
    }
    val photo = homeViewModel.specificPhoto.observeAsState().value
    SpecificFotoCard(photo?.urls?.raw ?: "")
}

@Preview
@Composable
fun PhotoDetailsPreview() {
    PhotoDetailsScreen(photoId = "")
}
