package com.cmaina.presentation.components.photoscards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun PhotoCardItem(
    blurHash: String,
    imageUrl: String?,
    photoID: String,
    navController: NavController
) {
    val res = LocalContext.current.resources

    Card(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(1.dp).clickable {
                navController.navigate("photo_detail_screen/$photoID")
            },
        shape = RoundedCornerShape(2),
    ) {
        AsyncImageBlur(
            blurHash = blurHash,
            imageUrl = imageUrl!!,
            resources = res
        )
    }
}
