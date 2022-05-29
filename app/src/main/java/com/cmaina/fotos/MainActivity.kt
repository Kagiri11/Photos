package com.cmaina.fotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.cmaina.fotos.ui.theme.FotosTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FotosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: HomeViewModel = getViewModel()) {
    LazyRow{
        val pictures = viewModel.pics.value
        pictures?.let {
            items(it) { pic ->
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = pic.domainUrls?.regular,
                    contentDescription = "image"
                )
            }
        }
    }
}
