package com.cmaina.fotos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmaina.presentation.components.bottomnav.FotosBottomNav
import com.cmaina.presentation.components.bottomnav.TopLevelDestinations
import com.cmaina.presentation.ui.navigation.NavGraph
import com.cmaina.presentation.ui.theme.FotosTheme
import com.cmaina.presentation.ui.theme.FotosWhite
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            FotosTheme {
                val navController = rememberNavController()
                val systemUIController = rememberSystemUiController()
                val scaffoldState = rememberScaffoldState()
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
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        if (isTopLevelDestination) {
                            FloatingActionButton(
                                onClick = {
                                    Toast.makeText(this, "Camera is pressed", Toast.LENGTH_SHORT)
                                        .show()
                                },

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = "camera"
                                )
                            }
                        }
                    },
                    scaffoldState = scaffoldState
                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(
                            paddingValues
                        )
                    )
                }
            }
        }
    }
}
