package com.cmaina.presentation.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmaina.presentation.R
import com.cmaina.presentation.navigation.NavGraph
import com.cmaina.presentation.navigation.bottomnav.FotosBottomNav
import com.cmaina.presentation.navigation.bottomnav.TopLevelDestinations
import com.cmaina.presentation.screens.settings.dataStore
import com.cmaina.presentation.ui.theme.FotosTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val mainViewModel: MainViewModel = getViewModel()
            val context = LocalContext.current
            val preferences = context.dataStore.data
            val navController = rememberNavController()
            val systemUIController = rememberSystemUiController()
            val scaffoldState = rememberScaffoldState()
            val isTopLevelDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route in TopLevelDestinations.map { it.route }
            SideEffect {
                Log.d("MainaViewModel"," Vm instance in activity : ${mainViewModel.toString()}")
                mainViewModel.fetchAppTheme(preferences)
                mainViewModel.changeSystemAppBarColors(systemUIController)
            }

            val appTheme = mainViewModel.isAppInDarkTheme.collectAsState().value

            FotosTheme(darkTheme = appTheme) {
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
                                backgroundColor = MaterialTheme.colors.primary

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = "camera",
                                    tint = MaterialTheme.colors.onPrimary
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
                        ),
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}
