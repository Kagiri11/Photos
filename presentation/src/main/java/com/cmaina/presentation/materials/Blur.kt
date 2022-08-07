package com.cmaina.presentation.materials

import android.annotation.SuppressLint
import android.view.View
import coil.request.ImageRequest

fun ImageRequest.Builder.blurPlaceHolder(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: ImageRequest.Builder) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@blurPlaceHolder.placeholder(drawable)
            response(this@blurPlaceHolder)
        }
    }
}

@SuppressLint("CheckResult")
fun ImageRequest.Builder.blurPlaceHolder(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: ImageRequest.Builder) -> Unit
) {
    targetView.post {
        blurPlaceHolder(blurString, targetView.width, targetView.height, blurHash, response)
    }
}
