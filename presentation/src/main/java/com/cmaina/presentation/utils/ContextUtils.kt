package com.cmaina.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import com.cmaina.presentation.screens.photodetails.PhotoDetailsViewModel

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun onResume(context: Context, viewModel: PhotoDetailsViewModel) {
    val uri = context.findActivity()?.intent?.data
    val code = uri.toString().substringAfter("code=")
    viewModel.authenticateUser(code)
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