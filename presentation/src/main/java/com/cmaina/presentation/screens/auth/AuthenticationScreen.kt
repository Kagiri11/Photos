package com.cmaina.presentation.screens.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.cmaina.presentation.navigation.Destination
import com.cmaina.presentation.ui.theme.FotosWhite
import com.cmaina.presentation.utils.findActivity
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthenticationScreen(
    navController: NavController,
    authViewModel: AuthViewModel = getViewModel()
) {
    val context = LocalContext.current
    val userIsAuthenticated = authViewModel.isUserAuthenticated.observeAsState().value
    DisposableEffect(key1 = true) {
        onResume(context, authViewModel)
        onDispose {
            // do something
        }
    }
    Log.d("", "Useris")
    LaunchedEffect(key1 = userIsAuthenticated) {
        when (userIsAuthenticated) {
            true -> {
                navController.navigate(Destination.HomeScreen.route)
            }
            false -> {}
            else -> {}
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(FotosWhite)
    ) {
        Button(
            onClick = {
                context.startAuth()
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Start")
        }
    }
}

fun Context.startAuth() {
    val uri = Uri.parse("https://unsplash.com/oauth/authorize")
        .buildUpon()
        .appendQueryParameter("client_id", "pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk")
        .appendQueryParameter("redirect_uri", "fotos://callback")
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("scope", "public")
        .build()
    val intent = Intent(Intent.ACTION_VIEW, uri)
    this.startActivity(intent)
}

fun onResume(context: Context, authViewModel: AuthViewModel) {
    val uri = context.findActivity()?.intent?.data
    val code = uri.toString().substringAfter("code=")
    authViewModel.authenticateUser(code)
}
