package com.cmaina.presentation.components.photoscards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PhotoCardItem(imageUrl: String?) {
    Card(
        modifier = Modifier
            .height((90..280).random().dp)
            .fillMaxWidth()
            .padding(bottom = 5.dp, start = 2.dp, end = 3.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
