package com.cmaina.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.cmaina.presentation.R
import com.cmaina.presentation.MainViewModel
import com.cmaina.presentation.components.settingscomponents.Setting
import com.cmaina.presentation.components.settingscomponents.SettingItemDialog
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    mainViewModel: MainViewModel,
    settingsViewModel: SettingsViewModel = getViewModel()
) {
    val isAppDarkTheme = mainViewModel.appTheme.collectAsState().value
    val isThemeDialogOpen = settingsViewModel.isThemeDialogOpen.collectAsState().value
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Settings screen" }
    ) {
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
                .semantics { contentDescription = "setting column" }
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,

        ) {
            Setting(
                settingName = "Display",
                settingAttribute = "Theme",
                attributeValue = if (isAppDarkTheme) "Dark" else "Light",
                settingIcon = R.drawable.ic_dark_mode
            ) {
                settingsViewModel.changeDialogOpenState()
            }
            SettingItemDialog(
                openDialog = isThemeDialogOpen,
                isAppInDarkMode = isAppDarkTheme,
                settingsViewModel = settingsViewModel,
                {
                    mainViewModel.changeAppTheme(false)
                },
                {
                    mainViewModel.changeAppTheme(true)
                }
            )
        }
    }
}
