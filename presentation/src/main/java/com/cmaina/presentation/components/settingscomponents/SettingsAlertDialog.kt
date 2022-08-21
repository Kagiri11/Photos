package com.cmaina.presentation.components.settingscomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cmaina.presentation.activities.MainViewModel
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.screens.settings.dataStore
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingItemDialog(
    openDialog: MutableState<Boolean>,
    mainViewModel: MainViewModel = getViewModel()
) {
    val context = LocalContext.current
    val dataStore = context.dataStore
    val isAppInDarkMode = mainViewModel.isAppInDarkTheme.collectAsState().value
    val isButtonSelected = remember { mutableStateOf(false) }
    if (openDialog.value) {
        Dialog(
            onDismissRequest = {
                openDialog.value = false
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(0.8f)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.width(14.dp))
                        FotosText(text = "Choose theme", textColor = MaterialTheme.colors.onPrimary)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                mainViewModel.changeAppTheme(dataStore, false)
                            }
                    ) {
                        RadioButton(
                            selected = isAppInDarkMode.not(),
                            onClick = {},
                            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.onPrimary)
                        )
                        FotosText(text = "Light")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                mainViewModel.changeAppTheme(dataStore, true)
                            }
                    ) {
                        RadioButton(
                            selected = isAppInDarkMode,
                            onClick = {},
                            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.onPrimary)
                        )
                        FotosText(text = "Dark")
                    }
                }
            }
        }
    }
}
