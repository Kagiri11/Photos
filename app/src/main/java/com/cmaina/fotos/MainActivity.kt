package com.cmaina.fotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.cmaina.presentation.components.bottomnav.FotosBottomNav
import com.cmaina.presentation.components.bottomnav.TopLevelDestinations
import com.cmaina.presentation.ui.navigation.NavGraph
import com.cmaina.presentation.ui.theme.FotosTheme
import com.cmaina.presentation.ui.theme.FotosWhite
import com.cmaina.presentation.screens.home.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            FotosTheme {
                val navController = rememberNavController()
                val systemUIController = rememberSystemUiController()
                val isTopLevelDestination =
                    navController.currentBackStackEntryAsState().value?.destination?.route in TopLevelDestinations.map { it.route }
                SideEffect {
                    systemUIController.setSystemBarsColor(FotosWhite)
                }
                Scaffold(
                    bottomBar = {
                        if (isTopLevelDestination) {
                            FotosBottomNav(navHostController = navController)
                        }
                    }
                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
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
/*
@Composable
fun SpecificPhotoDetail(photo: DomainPhotoListItem) {
    ConstraintLayout() {
        val (image, likebar, userarea) = createRefs()
        Card(
            modfier = Modifier
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
}*/
