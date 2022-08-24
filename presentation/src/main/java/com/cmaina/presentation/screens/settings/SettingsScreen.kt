package com.cmaina.presentation.screens.settings

import android.content.Context
import android.content.res.Resources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.cmaina.presentation.R
import com.cmaina.presentation.activities.MainViewModel
import com.cmaina.presentation.components.settingscomponents.Setting
import com.cmaina.presentation.components.settingscomponents.SettingItemDialog
import com.cmaina.presentation.materials.BlurHashDecoder
import com.skydoves.landscapist.glide.GlideImage

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Composable
fun SettingsScreen(
    mainViewModel: MainViewModel
) {
    val isAppDarkTheme = mainViewModel.isAppInDarkTheme.collectAsState().value
    val isThemeDialogOpen = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dataStore = context.dataStore
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (titleRef, settingsOptionsColumnRef) = createRefs()
        Text(
            text = "Settings",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 15.dp)
            }
        )
        Column(
            modifier = Modifier
                .constrainAs(settingsOptionsColumnRef) {
                    start.linkTo(parent.start, margin = 15.dp)
                    top.linkTo(titleRef.bottom, margin = 10.dp)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .clickable {
                },
            verticalArrangement = Arrangement.Top,

        ) {
            Setting(
                settingName = "Display",
                settingAttribute = "Theme",
                attributeValue = if (isAppDarkTheme) "Dark" else "Light",
                settingIcon = R.drawable.ic_dark_mode
            ) {
                isThemeDialogOpen.value = true
            }
            SettingItemDialog(
                isThemeDialogOpen,
                isAppDarkTheme,
                {
                    mainViewModel.changeAppTheme(dataStore = dataStore, false)
                },
                {
                    mainViewModel.changeAppTheme(dataStore = dataStore, true)
                }
            )
        }
    }
}

@Composable
fun AsyncImageBlur(
    modifier: Modifier = Modifier,
    blurHash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 700,
    resources: Resources,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val bitmap = BlurHashDecoder.decode(blurHash, 4, 3)
    val requestBuilder = Glide.with(LocalView.current).asBitmap().transition(BitmapTransitionOptions.withCrossFade(crossFadeAnimDuration))
    GlideImage(
        imageModel = imageUrl,
        modifier = modifier, placeHolder = bitmap?.asImageBitmap(),

    )
    /*AsyncImage(
        modifier = modiier,
        contentScale = contentScale,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            *//*.placeholder(bitmap?.toDrawable(resources = resources))*//*
            .crossfade(true).build(),
        contentDescription = contentDescription
    )*/
}
