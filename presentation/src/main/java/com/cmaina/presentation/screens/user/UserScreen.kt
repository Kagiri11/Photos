package com.cmaina.presentation.screens.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.presentation.R
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeTwoLightTheme
import com.cmaina.presentation.ui.theme.FotosWhite
import com.cmaina.presentation.viewmodels.UserViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun UserScreen(username: String, userViewModel: UserViewModel = getViewModel()) {
    userViewModel.fetchUser(username)
    userViewModel.fetchUserPhotos(username)
    Column(Modifier.fillMaxSize()) {
        TopPart()
        BottomPart()
    }
}

@Composable
fun TopPart() {
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
                modifier = Modifier.size(30.dp)
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

@Composable
fun BottomPart(userViewModel: UserViewModel = getViewModel()) {
    val user = userViewModel.user.observeAsState().value
    val photos = user?.total_photos ?: 0
    val followers = user?.followers_count ?: 0
    val following = user?.following_count ?: 0
    val userPhotos = userViewModel.userPhotos.observeAsState().value
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
            shape = RoundedCornerShape(50)
        ) {
            user?.profile_image?.large.let {
                AsyncImage(
                    model = it,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        user?.name?.let {
            FotosTitleText(
                text = it,
                textColor = FotosBlack,
                modifier = Modifier.constrainAs(username) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(userImage.bottom, margin = 10.dp)
                }
            )
        }

        FollowingSection(
            modifier = Modifier.constrainAs(followingSection) {
                top.linkTo(username.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            photos = photos,
            followers = followers,
            following = following
        )

        FollowAndMessageButtons(
            modifier = Modifier.constrainAs(followButtons) {
                top.linkTo(followingSection.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        userPhotos?.let {
            UserPhotos(
                modifier = Modifier.constrainAs(userPhotosRef) {
                    top.linkTo(followButtons.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                },
                photos = it
            )
        }
    }
}

@Composable
fun UserPhotos(modifier: Modifier = Modifier, photos: List<DomainPhotoListItem>) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(photos) { pic ->
            UserPhoto(userImageUrl = pic.domainUrls?.regular ?: "")
        }
    }
}

@Composable
fun FollowAndMessageButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        UserButton(
            text = "Follow",
            buttonColor = FotosBlack,
            textColor = FotosWhite,
            modifier = Modifier.weight(1f)
        )
        UserButton(
            text = "Message",
            buttonColor = FotosWhite,
            textColor = FotosBlack,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun UserButton(text: String, buttonColor: Color, textColor: Color, modifier: Modifier) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .height(55.dp),
        border = BorderStroke(width = 1.dp, color = FotosBlack),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        elevation = ButtonDefaults.elevation(0.dp)

    ) {
        FotosText(text = text, textColor = textColor)
    }
}

@Composable
fun UserPhoto(userImageUrl: String, description: String = "") {
    Card(
        Modifier
            .fillMaxWidth(0.9f)
            .height(200.dp),
        shape = RoundedCornerShape(5),
        elevation = 0.dp
    ) {
        AsyncImage(
            model = userImageUrl,
            contentDescription = description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

/*@Composable
fun LikesAndStuff(@DrawableRes resId: Int, text: String, colorFilter: ColorFilter? = null) {
    Row(Modifier.wrapContentWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = "",
            modifier = Modifier.size(20.dp),
            colorFilter = colorFilter
        )
        Spacer(modifier = Modifier.width(10.dp))
        FotosText(text = text, textColor = Color.Black)
    }
}*/

@Composable
fun DetailsColumn(text: String, number: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(100.dp)) {
        FotosText(text = text, textColor = FotosGreyShadeThreeLightTheme)
        FotosTitleText(text = number.toString(), textColor = FotosBlack)
    }
}

@Composable
fun FollowingSection(modifier: Modifier, photos: Int, followers: Int, following: Int) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DetailsColumn(text = "Photos", number = photos)
        Spacer(
            modifier = modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(FotosGreyShadeTwoLightTheme.copy(alpha = 0.2f))
        )
        DetailsColumn(text = "Followers", number = followers)
        Spacer(
            modifier = modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(FotosGreyShadeTwoLightTheme.copy(alpha = 0.2f))
        )
        DetailsColumn(text = "Following", number = following)
    }
}
