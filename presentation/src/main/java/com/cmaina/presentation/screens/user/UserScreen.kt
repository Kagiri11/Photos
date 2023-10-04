package com.cmaina.presentation.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.cmaina.presentation.R
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.components.userscreencomponents.FollowAndMessageButtons
import com.cmaina.presentation.components.userscreencomponents.FollowingSection
import com.cmaina.presentation.components.userscreencomponents.UserPhotos
import com.cmaina.presentation.screens.myPlaceholder
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun UserScreen(
    username: String,
    userViewModel: UserViewModel = getViewModel(),
    navController: NavController
) {
    val uiState = userViewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = true) {
        userViewModel.fetchUser(username)
    }
    when (uiState) {
        is UserUiState.Loading -> {}
        is UserUiState.Error -> {}
        is UserUiState.Success -> {
            Column(modifier = Modifier.fillMaxSize()) {

                TopPart(onBackPressed = { navController.navigateUp() })

                BottomPart(
                    userDetails = uiState.uiDetails,
                    onUserPhotoClicked = {
                        if (it != null) {
                            navController.navigate("photo_detail_screen/${it}")
                        }
                    })
            }
        }
    }

}

// region TopPart
@Composable
fun TopPart(onBackPressed: () -> Unit) {
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
                painter = painterResource(id = R.drawable.ic_baseline_chevron_left_24),
                contentDescription = "back",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = onBackPressed),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "more",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
            )
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
// endregion

// region BottomPart
@Composable
fun BottomPart(
    userDetails: UserUiDetails,
    onUserPhotoClicked: (String) -> Unit
) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .background(FotosGreyShadeOneLightTheme)
    ) {
        val (
            userImage, username,
            followingSection,
            followButtons,
            userPhotosRef
        ) = createRefs()

        Card(
            modifier = Modifier
                .size(80.dp)
                .constrainAs(userImage) {
                    top.linkTo(parent.top, (-40).dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = CircleShape
        ) {
            val painter = rememberImagePainter(data = userDetails.userImageUrl)
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .myPlaceholder(shape = CircleShape)
            )
        }

        FotosTitleText(
            text = userDetails.user.name ?: "",
            textColor = FotosBlack,
            modifier = Modifier.constrainAs(username) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(userImage.bottom, margin = 10.dp)
            }
        )

        FollowingSection(
            modifier = Modifier
                .constrainAs(followingSection) {
                    top.linkTo(username.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .wrapContentHeight(),
            photos = userDetails.numberOfPhotosByUser,
            followers = userDetails.followersCount,
            following = userDetails.followingCount
        )

        FollowAndMessageButtons(
            modifier = Modifier
                .constrainAs(followButtons) {
                    top.linkTo(followingSection.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight()
        )
        val flowOfPhotos = userDetails.userPhotos.collectAsLazyPagingItems()
        UserPhotos(
            modifier = Modifier
                .constrainAs(userPhotosRef) {
                    top.linkTo(followButtons.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(MaterialTheme.colors.primary),
            photos = flowOfPhotos,
            onUserPhotoClicked = onUserPhotoClicked
        )
    }
}

// endregion
