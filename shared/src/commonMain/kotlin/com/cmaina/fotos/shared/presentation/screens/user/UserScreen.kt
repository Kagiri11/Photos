package com.cmaina.fotos.shared.presentation.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.cmaina.fotos.shared.presentation.components.userscreencomponents.UserPhotos
import com.cmaina.fotos.shared.presentation.ui.theme.FotosBlack
import com.cmaina.fotos.shared.presentation.utils.PainterResource
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.fotos.shared.presentation.components.userscreencomponents.FollowAndMessageButtons
import com.cmaina.fotos.shared.presentation.components.userscreencomponents.FollowingSection
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun UserScreen(
    onScreenLoad: () -> Unit,
    onBackPressed: () -> Unit,
    onUserPhotoClicked: (String) -> Unit,
    uiState: UserUiState
) {

    LaunchedEffect(key1 = true) {
        onScreenLoad()
    }

    when (uiState) {
        is UserUiState.Loading -> {}
        is UserUiState.Error -> {}
        is UserUiState.Success -> {
            Column(modifier = Modifier.fillMaxSize()) {
                UserDetailsScreen(
                    onBackPressed = onBackPressed,
                    userUiDetails = uiState.uiDetails,
                    onUserPhotoClicked = onUserPhotoClicked
                )

            }
        }
    }

}

@Composable
fun UserDetailsScreen(
    onBackPressed: () -> Unit,
    userUiDetails: UserUiDetails,
    onUserPhotoClicked: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Top Part
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .background(color = MaterialTheme.colors.primary),
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Image(
//                painter = painterResource(id = R.drawable.ic_baseline_chevron_left_24),
                    painter = PainterResource.DownloadArrow(),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(onClick = onBackPressed),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = PainterResource.MoreVert(),
                    contentDescription = "more",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        }

        Column {
            Card(
                modifier = Modifier
                    .size(80.dp),
                shape = CircleShape
            ) {
                val painter = asyncPainterResource(data = userUiDetails.userImageUrl)
                KamelImage(
                    resource = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            FotosTitleText(
                text = userUiDetails.userName,
                textColor = FotosBlack,
                modifier = Modifier
            )

            FollowingSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                photos = userUiDetails.numberOfPhotosByUser,
                followers = userUiDetails.followersCount,
                following = userUiDetails.followingCount
            )

            FollowAndMessageButtons(
                modifier = Modifier.wrapContentHeight()
            )

            val flowOfPhotos = userUiDetails.userPhotos.collectAsLazyPagingItems()
            UserPhotos(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(MaterialTheme.colors.primary),
                photos = flowOfPhotos,
                onUserPhotoClicked = onUserPhotoClicked
            )

        }

        // Bottom Part
    }
}

// endregion
