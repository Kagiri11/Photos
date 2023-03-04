package com.cmaina.tacc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.cmaina.tacc.navigation.AppNavHost
import com.cmaina.tacc.ui.theme.ThermondoAndroidCodingChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            ThermondoAndroidCodingChallengeTheme {
                AppNavHost(navController = navHostController)
            }
        }
    }
}
