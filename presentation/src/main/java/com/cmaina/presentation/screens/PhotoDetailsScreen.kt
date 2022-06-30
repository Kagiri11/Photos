package com.cmaina.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(homeViewModel: HomeViewModel = getViewModel()) {
    val photo = homeViewModel.randomPhoto.value
    Box(Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            model = ImageRequest.Builder(LocalContext.current)
                .crossfade(true)
                .data(photo?.domainUrls?.regular)
                .build(),
            contentDescription = photo?.description,
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier.fillMaxWidth(0.4f)
                .fillMaxHeight(0.3f)
//                .background(color = Color.Blue)
                .align(Alignment.BottomStart)
        ) {
            FotosText(text = "Phounius", textColor = FotosBlack)
            FotosText(text = "30 likes", textColor = FotosBlack)
            FotosText(text = "25 downloads", textColor = FotosBlack)
            FotosText(text = "Chinese Herbaceous Peony", textColor = FotosBlack)
        }
    }
}

@Preview
@Composable
fun PhotoDetailsPreview() {
    PhotoDetailsScreen()
}
