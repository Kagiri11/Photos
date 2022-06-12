package com.cmaina.fotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cmaina.presentation.screens.MainScreen
import com.cmaina.presentation.ui.theme.FotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FotosTheme {
                MainScreen()
            }
        }
    }
}

/*@Composable
fun Greeting(viewModel: HomeViewModel = getViewModel()) {
    val myPictures = viewModel.pics.collectAsState().value
    LazyColumn {
        items(myPictures) { pic ->
            AsyncImage(model = pic.domainUrls?.regular, contentDescription = "image")
        }
    }
}*/
