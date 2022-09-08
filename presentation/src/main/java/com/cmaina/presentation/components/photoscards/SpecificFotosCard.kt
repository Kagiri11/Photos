package com.cmaina.presentation.components.photoscards

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.SpecificFotosCard(blurHash: String, imageUrl: String) {
    val res = LocalContext.current.resources
    Card(
        modifier = Modifier.weight(0.7f).fillMaxWidth(),
        shape = RectangleShape
    ) {
        AsyncImageBlur(
            blurHash = blurHash,
            imageUrl = imageUrl,
            resources = res,
            modifier = Modifier.fillMaxSize()
        )
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
