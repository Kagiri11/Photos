package com.cmaina.fotos.shared.presentation.screens.settings

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
import com.cmaina.fotos.shared.presentation.utils.PainterResource
import com.cmaina.presentation.components.settingscomponents.Setting
import com.cmaina.presentation.components.settingscomponents.SettingItemDialog


@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel
) {
    val isAppDarkTheme = settingsViewModel.appTheme.collectAsState().value
    val isThemeDialogOpen = settingsViewModel.isThemeDialogOpen.collectAsState().value
    Column {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
        )

        Column(
            modifier = Modifier
                .semantics { contentDescription = "setting column" }
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,

            ) {
            Setting(
                settingName = "Display",
                settingAttribute = "Theme",
                attributeValue = if (isAppDarkTheme) "Dark" else "Light",
                settingIcon = 0
            ) {
                settingsViewModel.changeDialogOpenState()
            }
            SettingItemDialog(
                openDialog = isThemeDialogOpen,
                isAppInDarkMode = isAppDarkTheme,
                settingsViewModel = settingsViewModel,
                {
                    settingsViewModel.changeAppTheme(false)
                },
                {
                    settingsViewModel.changeAppTheme(true)
                }
            )
        }
    }
}
