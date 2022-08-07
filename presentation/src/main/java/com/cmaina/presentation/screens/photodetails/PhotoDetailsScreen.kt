package com.cmaina.presentation.screens.photodetails

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cmaina.presentation.R
import com.cmaina.presentation.components.photoscards.SpecificFotoCard
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.ui.navigation.Destination
import com.cmaina.presentation.ui.theme.FotosBlack
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoDetailsScreen(
    photoDetailsViewModel: PhotoDetailsViewModel = getViewModel(),
    photoId: String,
    navController: NavController
) {
    SideEffect {
        photoDetailsViewModel.fetchPhoto(photoId)
    }
    val url = photoDetailsViewModel.photoUrlLink.observeAsState().value ?: ""
    val userName = photoDetailsViewModel.username.observeAsState().value ?: ""
    val userPhotoImageUrl = photoDetailsViewModel.userPhotoUrl.observeAsState().value ?: ""
    val numberOfLikes = photoDetailsViewModel.numberOfLikes.observeAsState().value ?: 0
    Column(modifier = Modifier.fillMaxSize()) {
        SpecificFotoCard(url)
        LikeAndDownloadSection(
            userName = userName,
            userPhotoUrl = userPhotoImageUrl,
            numberOfLikes = numberOfLikes,
            navController = navController
        )
    }
}

@Composable
fun ColumnScope.LikeAndDownloadSection(userName: String, userPhotoUrl: String, numberOfLikes: Int, navController: NavController) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth().weight(0.1f),

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
                contentDescription = "Download photo",
                tint = FotosBlack,
                modifier = Modifier.size(35.dp).constrainAs(downloadButton) {
                    top.linkTo(userSection.top)
                    end.linkTo(likeButton.start, margin = 20.dp)
                }
            )
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "like photo",
                tint = FotosBlack,
                modifier = Modifier.size(35.dp).constrainAs(likeButton) {
                    top.linkTo(userSection.top)
                    end.linkTo(parent.end, margin = 20.dp)
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
    onClick: () -> Unit
) {
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "user image",
                modifier = Modifier.size(35.dp).clip(
                    CircleShape
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            FotosText(userName, textColor = FotosBlack)
        }
        Spacer(modifier = Modifier.height(3.dp))
        FotosText("$numberOfLikes likes", textColor = FotosBlack)
    }
}

@Preview
@Composable
fun PhotoDetailsPreview() {
}
