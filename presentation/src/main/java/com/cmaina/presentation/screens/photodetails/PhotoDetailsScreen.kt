package com.cmaina.presentation.screens.photodetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.cmaina.presentation.R
import com.cmaina.presentation.components.dialogs.NotAuthenticatedDialog
import com.cmaina.presentation.components.photoscards.PhotosPager
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.utils.findActivity
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(
    photoDetailsViewModel: PhotoDetailsViewModel = getViewModel(),
    photoId: String,
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        photoDetailsViewModel.fetchPhoto(photoId)
    }
    val userName = photoDetailsViewModel.username.observeAsState("").value
    val userPhotoImageUrl = photoDetailsViewModel.userPhotoUrl.observeAsState("").value
    val numberOfLikes = photoDetailsViewModel.numberOfLikes.observeAsState(0).value
    val relatedImages = photoDetailsViewModel.relatedPhotosStrings.observeAsState(listOf()).value
    val isThereMessageToTheUser = photoDetailsViewModel.messageToUser.collectAsState().value
    val userIsAuthenticated = photoDetailsViewModel.userIsAuthenticated.collectAsState().value
    val userLikedThePhoto = photoDetailsViewModel.photoLikedByUser.observeAsState().value ?: false
    val context = LocalContext.current
    DisposableEffect(key1 = true) {
        onResume(context, photoDetailsViewModel)
        onDispose {
            // do something
        }
    }

    if (isThereMessageToTheUser) {
        NotAuthenticatedDialog(
            openDialog = true,
            onDismissed = { photoDetailsViewModel.changeMessageStatus() },
            onUserAcceptedAction = { context.startAuth() }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        var photoId = ""
        PhotosPager(images = relatedImages) {
            photoId = it
            photoDetailsViewModel.checkIfPhotoIsLiked(it)
        }
        LikeAndDownloadSection(
            userName = userName,
            userPhotoUrl = userPhotoImageUrl,
            numberOfLikes = numberOfLikes,
            navController = navController,
            userIsAuthenticated = userIsAuthenticated,
            userHasLikedPhoto = userLikedThePhoto,
            onLikeClick = {
                photoDetailsViewModel.likePhoto(photoId)
            },
            onDownloadClick = {}
        )
    }
}

@Composable
fun ColumnScope.LikeAndDownloadSection(
    userName: String,
    userPhotoUrl: String,
    numberOfLikes: Int,
    navController: NavController,
    userIsAuthenticated: Boolean,
    userHasLikedPhoto: Boolean,
    onLikeClick: () -> Unit,
    onDownloadClick: () -> Unit
) {
    val iconPainter =
        painterResource(id = if (userHasLikedPhoto) R.drawable.ic_favourite else R.drawable.ic_favorite_outlined)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (userSection, likesSection, likeButton, downloadButton) = createRefs()
            UserSection(
                ref = userSection,
                numberOfLikes = numberOfLikes,
                userName = userName,
                userImageUrl = userPhotoUrl
            ) {
                navController.navigate("user_screen/$userName")
            }
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
                    .clickable {
                        onDownloadClick()
                    }
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
                    .clickable {
                        onLikeClick()
                    }
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
                    .clip(
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(5.dp))
            FotosText(text = userName, textColor = MaterialTheme.colors.onPrimary)
        }
        Spacer(modifier = Modifier.height(3.dp))
        FotosText(text = "$numberOfLikes likes", textColor = MaterialTheme.colors.onPrimary)
    }
}

fun onResume(context: Context, viewModel: PhotoDetailsViewModel) {
    val uri = context.findActivity()?.intent?.data
    val code = uri.toString().substringAfter("code=")
    viewModel.authenticateUser(code)
}

fun Context.startAuth() {
    val uri = Uri.parse("https://unsplash.com/oauth/authorize")
        .buildUpon()
        .appendQueryParameter("client_id", "pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk")
        .appendQueryParameter("redirect_uri", "fotos://callback")
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("scope", "public")
        .build()
    val intent = Intent(Intent.ACTION_VIEW, uri)
    this.startActivity(intent)
}

@Preview
@Composable
fun PhotoDetailsPreview() {
}
