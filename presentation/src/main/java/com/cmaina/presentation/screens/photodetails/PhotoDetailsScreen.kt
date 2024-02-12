package com.cmaina.presentation.screens.photodetails

import android.telephony.mbms.StreamingServiceInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.compose.rememberImagePainter
import com.cmaina.presentation.R
import com.cmaina.presentation.components.dialogs.NotAuthenticatedDialog
import com.cmaina.presentation.components.photoscards.PhotosPager
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.utils.onResume
import com.cmaina.presentation.utils.startAuth
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(
    onInitialLoadEvent: () -> Unit,
    onUserSectionClickedEvent: (String) -> Unit,
    onImageLikedEvent: () -> Unit,
    onDialogDismissedEvent: () -> Unit,
    onPageSwappedEvent: (String) -> Unit,
    messageIsPresent: Boolean,
    onUserRequestsAuthenticationEvent: (String) -> Unit,
    uiState: PhotoDetailsUiState
) {
    LaunchedEffect(key1 = true) {
        onInitialLoadEvent()
    }
    val context = LocalContext.current
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
            onUserAcceptedAction = { context.startAuth() }
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
    val iconPainter = painterResource(
        id = if (userHasLikedPhoto) R.drawable.ic_favourite else R.drawable.ic_favorite_outlined
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (userSection, likeButton, downloadButton) = createRefs()
            UserSection(
                ref = userSection,
                numberOfLikes = numberOfLikes,
                userName = userName,
                userImageUrl = userPhotoUrl,
                onClick = onUserSectionClicked
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_download),
                contentDescription = stringResource(R.string.download_photo_description),
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .size(35.dp)
                    .constrainAs(downloadButton) {
                        top.linkTo(userSection.top)
                        end.linkTo(likeButton.start, margin = 20.dp)
                    }
                    .clickable(onClick = onDownloadClick)
            )
            Icon(
                painter = iconPainter,
                contentDescription = stringResource(R.string.like_photo_description),
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .size(35.dp)
                    .constrainAs(likeButton) {
                        top.linkTo(userSection.top)
                        end.linkTo(parent.end, margin = 20.dp)
                    }
                    .clickable(onClick = onLikeClick)
            )
        }
    }
}

@Composable
fun ConstraintLayoutScope.UserSection(
    ref: ConstrainedLayoutReference,
    userImageUrl: String,
    numberOfLikes: Int,
    userName: String,
    onClick: () -> Unit,
) {

    val painter = rememberImagePainter(data = userImageUrl)

    Column(
        modifier = Modifier.constrainAs(ref) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, margin = 10.dp)
            start.linkTo(parent.start, margin = 20.dp)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.user_image_description),
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(5.dp))
            FotosText(text = userName, textColor = MaterialTheme.colors.onPrimary)
        }
        Spacer(modifier = Modifier.height(3.dp))
        FotosText(
            text = stringResource(R.string.number_of_likes, numberOfLikes),
            textColor = MaterialTheme.colors.onPrimary
        )
    }
}


