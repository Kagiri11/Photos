package com.cmaina.fotos.shared.presentation.screens.photodetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmaina.fotos.shared.presentation.components.dialogs.NotAuthenticatedDialog
import com.cmaina.fotos.shared.presentation.components.photoscards.AsyncImageBlur
import com.cmaina.fotos.shared.presentation.utils.AppContext
import com.cmaina.presentation.components.photoscards.PhotosPager
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.components.photostext.FotosTitleText

@Composable
fun PhotoDetailsScreen(
    onInitialLoadEvent: () -> Unit,
    onUserSectionClickedEvent: (String) -> Unit,
    onImageLikedEvent: () -> Unit,
    onDialogDismissedEvent: () -> Unit,
    onPageSwappedEvent: (String) -> Unit,
    messageIsPresent: Boolean,
    onUserRequestsAuthenticationEvent: (String) -> Unit,
    uiState: PhotoDetailsUiState,
    context: AppContext
) {
    LaunchedEffect(key1 = true) {
        onInitialLoadEvent()
    }
    DisposableEffect(key1 = true) {
//        onResume(context, onUserRequestsAuthenticationEvent)
        onDispose {
            // do something
        }
    }

    if (messageIsPresent) {
        NotAuthenticatedDialog(
            openDialog = true,
            onDismissed = {
                onDialogDismissedEvent()
            },
            onUserAcceptedAction = {
                // start auth process
            }
        )
    }

    when (uiState) {
        is PhotoDetailsUiState.Loading -> {}
        is PhotoDetailsUiState.Error -> {}
        is PhotoDetailsUiState.Success -> {

            with(uiState.details) {
                var page by remember { mutableStateOf(0) }

                Column(modifier = Modifier.fillMaxSize()) {
                    PhotosPager(
                        images = relatedImages,
                        onPageSwapped = {
                            onPageSwappedEvent(it)
                        },
                        pageInIteration = { page = it }
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(relatedImages.size) { iteration ->
                            val color = if (iteration == page) Color.Gray else Color.Black
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(15.dp)

                            )
                        }

                    }

                    LikeAndDownloadSection(
                        userName = userName,
                        userPhotoUrl = userPhotoImageUrl,
                        numberOfLikes = numberOfLikes,
                        userHasLikedPhoto = photoIsLikedByUser,
                        onLikeClick = {

                            onImageLikedEvent()
                        },
                        onDownloadClick = {},
                        onUserSectionClicked = { onUserSectionClickedEvent(userName) }
                    )
                }
            }
        }
    }
}

@Composable
fun ColumnScope.LikeAndDownloadSection(
    userName: String,
    userPhotoUrl: String,
    numberOfLikes: Int,
    userHasLikedPhoto: Boolean,
    onLikeClick: () -> Unit,
    onDownloadClick: () -> Unit,
    onUserSectionClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            AsyncImageBlur(
                modifier = Modifier,
                imageUrl = userPhotoUrl,
                contentDescription = "",
                blurHash = ""
            )
            FotosText(modifier = Modifier, text = userName)
            Image(
                painter = com.cmaina.fotos.shared.presentation.utils.PainterResource.DownloadArrow(),
                contentDescription = ""
            )
            Image(
                painter = com.cmaina.fotos.shared.presentation.utils.PainterResource.Likes(),
                contentDescription = ""
            )
        }

        FotosTitleText("$numberOfLikes likes", MaterialTheme.colors.onPrimary)
    }

}
