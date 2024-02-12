package com.cmaina.fotos.shared.presentation.screens.app

import androidx.compose.runtime.Composable
import com.cmaina.fotos.shared.presentation.navigation.FotosNavHost
import moe.tlaster.precompose.PreComposeApp

@Composable
fun App() {
    PreComposeApp {
        FotosNavHost()
    }
}