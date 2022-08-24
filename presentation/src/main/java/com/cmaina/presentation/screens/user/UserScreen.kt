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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmaina.presentation.R
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.components.userscreencomponents.FollowAndMessageButtons
import com.cmaina.presentation.components.userscreencomponents.FollowingSection
import com.cmaina.presentation.components.userscreencomponents.UserPhotos
import com.cmaina.presentation.screens.myPlaceholder
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel

@Composable
fun UserScreen(
    username: String,
    userViewModel: UserViewModel = getViewModel(),
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        userViewModel.fetchUser(username)
        userViewModel.fetchUserPhotos(username)
    }
    Column(Modifier.fillMaxSize()) {
        TopPart(navController = navController)
        BottomPart(navController = navController)
    }
}

// region TopPart
@Composable
fun TopPart(navController: NavController) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.15f)
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
                modifier = Modifier.size(30.dp).clickable {
                    navController.navigateUp()
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "more"
            )
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
// endregion

// region BottomPart
@Composable
fun BottomPart(userViewModel: UserViewModel = getViewModel(), navController: NavController) {
    val user = userViewModel.user.observeAsState().value
    val photos = userViewModel.usersPhotoCount.collectAsState().value
    val userImageUrl = userViewModel.userImageUrl.collectAsState().value
    val followers = userViewModel.usersFollowersCount.collectAsState().value
    val following = userViewModel.usersFollowingCount.collectAsState().value
    val userPhotos = userViewModel.userPhotos.observeAsState().value?.collectAsLazyPagingItems()
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
            GlideImage(
                imageModel = userImageUrl,
                modifier = Modifier.fillMaxSize().myPlaceholder(shape = CircleShape),
            )
        }

        FotosTitleText(
            text = user?.name ?: "unknown user",
            textColor = FotosBlack,
            modifier = Modifier.constrainAs(username) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(userImage.bottom, margin = 10.dp)
            }
        )

        FollowingSection(
            modifier = Modifier.constrainAs(followingSection) {
                top.linkTo(username.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth().wrapContentHeight(),
            photos = photos,
            followers = followers,
            following = following
        )

        FollowAndMessageButtons(
            modifier = Modifier.constrainAs(followButtons) {
                top.linkTo(followingSection.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.wrapContentHeight()
        )
        UserPhotos(
            modifier = Modifier.constrainAs(userPhotosRef) {
                top.linkTo(followButtons.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }.fillMaxWidth().fillMaxHeight(0.5f),
            photos = userPhotos,
            navController = navController
        )
    }
}

// endregion
