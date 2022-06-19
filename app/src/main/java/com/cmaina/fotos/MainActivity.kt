package com.cmaina.fotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import coil.compose.AsyncImage
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.fotos.di.PhotoDetailsScreen
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.ui.theme.FotosGreyShadeOneLightTheme
import com.cmaina.presentation.ui.theme.FotosTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            FotosTheme {
//                val controller = rememberAndroidSystemUiController()
                val systemUIController = rememberSystemUiController()
                SideEffect {
                    systemUIController.setSystemBarsColor(Color.Transparent)
                }
                PhotoDetailsScreen()
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
    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    ) {
    }
}

@Composable
fun BottomPart() {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
    }
}

@Composable
fun UserImage(url: String, size: Int) {
    Card(
        modifier = Modifier.size(size.dp),
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
            LikesAndStuff(resId = R.drawable.ic_likes, text = photo.likes.toString())
            LikesAndStuff(
                resId = R.drawable.ic_baseline_message_24,
                text = photo.created_at ?: "24",
                colorFilter = ColorFilter.tint(
                    FotosGreyShadeOneLightTheme
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "bookmark",
                colorFilter = ColorFilter.tint(
                    FotosGreyShadeOneLightTheme
                )
            )
        }
    }
}
