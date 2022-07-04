package com.cmaina.presentation.components.photoscards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cmaina.presentation.ui.navigation.Destination

@Composable
fun PhotoCardItem(imageUrl: String?, username: String, navController: NavController) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(bottom = 5.dp, start = 2.dp, end = 3.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true).build(),
            contentDescription = "image flani",
            modifier = Modifier.fillMaxSize().clickable {
                navController.navigate("user_screen/$username")
            },
            contentScale = ContentScale.Crop
        )
    }
}
