package com.cmaina.presentation.components.photoscards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cmaina.presentation.R

@Composable
fun SpecificFotoCard() {
    Card(
        modifier = Modifier.height(300.dp).fillMaxWidth(0.9f),
        backgroundColor = Color.Blue,
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://images.unsplash.com/photo-1651578403332-65e82b38b045?crop=entropy&cs=tinysrgb&fm=jpg&ixid=MnwzMjU1OTB8MHwxfGFsbHx8fHx8fHx8fDE2NTY3NTc4ODg&ixlib=rb-1.2.1&q=80")
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (downloadButtonRef, likesRef, userRef) = createRefs()
                UserAndDescription(
                    modifier = Modifier.constrainAs(userRef) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                    }.blur(30.dp)
                )
                Button(
                    onClick = {},
                    modifier = Modifier.constrainAs(downloadButtonRef) {
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    }.height(50.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_download),
                        contentDescription = "download"
                    )
                }
            }
        }
    }
}

@Composable
fun UserAndDescription(modifier: Modifier) {
    Card(
        modifier = modifier.height(60.dp).width(150.dp)
            .blur(30.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded),
        shape = RoundedCornerShape(60.dp)
    ) {
    }
}
