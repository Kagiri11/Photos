package com.cmaina.presentation.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cmaina.presentation.components.photostext.FotosText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NotAuthenticatedDialog(
    openDialog: Boolean,
    onDismissed: () -> Unit,
    onUserAcceptedAction: () -> Unit
) {
    if (openDialog) {
        Dialog(
            onDismissRequest = { onDismissed() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Card(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(0.8f)
                    .semantics { contentDescription = "Setting dialog" }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = { onUserAcceptedAction() },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        FotosText(text = "Perform action")
                    }
                }
            }
        }
    }
}
