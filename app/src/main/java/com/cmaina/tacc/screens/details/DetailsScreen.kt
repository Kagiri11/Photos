package com.cmaina.tacc.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.cmaina.tacc.screens.list.ListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen(listViewModel: ListViewModel = getViewModel()) {
    val photo = listViewModel.photoDetail.collectAsState().value
    Box(modifier = Modifier.fillMaxSize()) {
        val painter = rememberAsyncImagePainter(model = photo?.imgSrc)
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f)
                .align(alignment = Alignment.Center)
        )
    }
}
