package com.cmaina.fotos.di

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmaina.fotos.HomeViewModel
import com.cmaina.fotos.SpecificPhotoDetail
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(homeViewModel: HomeViewModel = getViewModel()) {
    val photo = homeViewModel.randomPhoto.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FotosGreyShadeOneLightTheme)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(Alignment.TopCenter)
                .background(Color.Transparent),
            shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ) {
        }
        /*Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.TopCenter),
            shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = "https://images.unsplash.com/photo-1652794121113-1a2a9563daaf?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzMjU1OTB8MHwxfHJhbmRvbXx8fHx8fHx8fDE2NTU0MDM0MTg&ixlib=rb-1.2.1&q=80&w=1080",
                contentDescription = "randomImage",
                contentScale = ContentScale.Crop
            )
        }*/
        if (photo != null) {
            SpecificPhotoDetail(photo = photo)
        }
    }
}
