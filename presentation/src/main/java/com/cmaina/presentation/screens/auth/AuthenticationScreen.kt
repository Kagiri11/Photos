package com.cmaina.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.cmaina.presentation.navigation.Destination
import com.cmaina.presentation.ui.theme.FotosWhite

@Composable
fun AuthenticationScreen(navController: NavController) {
    Box(Modifier.fillMaxSize().background(FotosWhite)) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Start")
        }
    }
}
