package com.cmaina.presentation.screens.photodetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmaina.presentation.components.photoscards.SpecificFotoCard
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.cmaina.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(homeViewModel: HomeViewModel = getViewModel(), photoId: String) {
    val photo = homeViewModel.randomPhoto.value
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(FotosGreyShadeOneLightTheme),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items((0..10).toList()) {
            SpecificFotoCard()
        }
    }
}

@Preview
@Composable
fun PhotoDetailsPreview() {
    PhotoDetailsScreen(photoId = "")
}
