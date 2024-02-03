package com.cmaina.presentation.components.photoscards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PhotoCardItem(
    blurHash: String,
    imageUrl: String,
    contentDescription: String,
    onPhotoClicked: () -> Unit
) {

    Card(
        modifier = Modifier
            .height((180..250).random().dp)
            .fillMaxWidth()
            .padding(1.dp)
            .clickable(onClick = onPhotoClicked),
        shape = RoundedCornerShape(2),
    ) {
        AsyncImageBlur(
            blurHash = blurHash,
            imageUrl = imageUrl,
            contentDescription = contentDescription
        )
    }
}
