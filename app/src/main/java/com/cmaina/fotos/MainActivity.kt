package com.cmaina.fotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.compose.AsyncImage
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.screens.HomeScreen
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeTwoLightTheme
import com.cmaina.presentation.ui.theme.FotosTheme
import com.cmaina.presentation.ui.theme.FotosWhite
import com.cmaina.presentation.viewmodels.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            FotosTheme {
                val systemUIController = rememberSystemUiController()
                SideEffect {
                    systemUIController.setSystemBarsColor(FotosWhite)
                }
                HomeScreen()
            }
        }
    }
}

@Composable
fun UserScreen() {
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
                painter = painterResource(id = com.cmaina.presentation.R.drawable.ic_baseline_chevron_left_24),
                contentDescription = "back",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = com.cmaina.presentation.R.drawable.ic_more_vert),
                contentDescription = "more"
            )
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Composable
fun BottomPart(viewModel: HomeViewModel = getViewModel()) {
    val photos = viewModel.pics.observeAsState().value
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
            userPhotos
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
            AsyncImage(
                model = "https://images.unsplash.com/photo-1587613865763-4b8b0d19e8ab?ixlib=rb-1.2.1&w=1080&fit=max&q=80&fm=jpg&crop=entropy&cs=tinysrgb",
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        FotosTitleText(
            text = "Kate Lingard",
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
            }
        )

        FollowAndMessageButtons(
            modifier = Modifier.constrainAs(followButtons) {
                top.linkTo(followingSection.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        /*UserPhotos(
            modifier = Modifier.constrainAs(userPhotos) {
                top.linkTo(followButtons.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            },
            photos = photos
        )*/
    }
}

@Composable
fun FollowingSection(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DetailsColumn(text = "Photos", number = 219)
        Spacer(
            modifier = modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(FotosGreyShadeTwoLightTheme.copy(alpha = 0.2f))
        )
        DetailsColumn(text = "Followers", number = 3296)
        Spacer(
            modifier = modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(FotosGreyShadeTwoLightTheme.copy(alpha = 0.2f))
        )
        DetailsColumn(text = "Following", number = 542)
    }
}

@Composable
fun DetailsColumn(text: String, number: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(100.dp)) {
        FotosText(text = text, textColor = FotosGreyShadeThreeLightTheme)
        FotosTitleText(text = number.toString(), textColor = FotosBlack)
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
fun UserPhotos(modifier: Modifier = Modifier, photos: List<DomainPhotoListItem>) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(photos) { pic ->
            UserPhoto(userImageUrl = pic.domainUrls?.regular ?: "")
        }
    }
}

@Composable
fun UserPhoto(userImageUrl: String, description: String = "") {
    Card(
        Modifier
            .fillMaxWidth(0.9f)
            .height(200.dp),
        shape = RoundedCornerShape(10),
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

@Composable
fun UserImage(url: String, size: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.size(size.dp),
        shape = RoundedCornerShape(50)
    ) {
        AsyncImage(
            model = url,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
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
}

@Composable
fun SpecificPhotoDetail(photo: DomainPhotoListItem) {
    ConstraintLayout() {
        val (image, likebar, userarea) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = photo.domainUrls?.regular,
                contentDescription = "randomImage",
                contentScale = ContentScale.Crop
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(likebar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(image.bottom, margin = 10.dp)
                }
                .height(40.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LikesAndStuff(
                resId = com.cmaina.presentation.R.drawable.ic_likes,
                text = photo.likes.toString()
            )
            LikesAndStuff(
                resId = com.cmaina.presentation.R.drawable.ic_baseline_message_24,
                text = photo.created_at ?: "24",
                colorFilter = ColorFilter.tint(
                    FotosGreyShadeOneLightTheme
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = com.cmaina.presentation.R.drawable.ic_bookmark),
                contentDescription = "bookmark",
                colorFilter = ColorFilter.tint(
                    FotosGreyShadeOneLightTheme
                )
            )
        }
    }
}
