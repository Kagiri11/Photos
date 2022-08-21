package com.cmaina.presentation.screens.settings

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.cmaina.presentation.R
import com.cmaina.presentation.activities.MainViewModel
import com.cmaina.presentation.components.settingscomponents.Setting
import com.cmaina.presentation.components.settingscomponents.SettingItemDialog
import com.cmaina.presentation.ui.theme.FotosBlack
import org.koin.androidx.compose.getViewModel

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Composable
fun SettingsScreen(mainViewModel: MainViewModel = getViewModel()) {
    val isThemeDialogOpen = remember { mutableStateOf(false) }
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (titleRef, settingsOptionsColumnRef) = createRefs()
        Text(
            text = "Settings",
            style = MaterialTheme.typography.h1.copy(color = FotosBlack),
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
                attributeValue = "Dark",
                settingIcon = R.drawable.ic_dark_mode
            ) {
                isThemeDialogOpen.value = true
            }
            SettingItemDialog(isThemeDialogOpen)
        }
    }
}
