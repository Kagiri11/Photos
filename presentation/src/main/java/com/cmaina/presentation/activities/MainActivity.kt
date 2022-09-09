package com.cmaina.presentation.activities

import android.content.Intent
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmaina.presentation.R
import com.cmaina.presentation.navigation.NavGraph
import com.cmaina.presentation.navigation.bottomnav.FotosBottomNav
import com.cmaina.presentation.navigation.bottomnav.TopLevelDestinations
import com.cmaina.presentation.ui.theme.FotosTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private lateinit var appUpdateManager: AppUpdateManager

    private val monitorAppUpdateRequest = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        installSplashScreen()
        val mainViewModel: MainViewModel by inject()

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    monitorAppUpdateRequest
                )
            }
        }
        setContent {
            val navController = rememberNavController()
            val systemUIController = rememberSystemUiController()
            mainViewModel.fetchAppTheme(systemUiController = systemUIController)
            val scaffoldState = rememberScaffoldState()
            val isTopLevelDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route in TopLevelDestinations.map { it.route }

            val appTheme = mainViewModel.appTheme.collectAsState().value
            val userIsAuthenticated = mainViewModel.userIsAuthenticated.collectAsState().value
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
                        if (isTopLevelDestination && userIsAuthenticated) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == monitorAppUpdateRequest) {
            if (resultCode != RESULT_OK) {
                Log.d("MY_APP", "Update flow failed! Result code: $resultCode")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo, AppUpdateType.IMMEDIATE, this, monitorAppUpdateRequest
                )
            }
        }
    }
}
