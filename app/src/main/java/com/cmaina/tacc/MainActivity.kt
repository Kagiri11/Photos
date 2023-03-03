package com.cmaina.tacc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cmaina.tacc.ui.theme.ThermondoAndroidCodingChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThermondoAndroidCodingChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Hello mars")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello Mars")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThermondoAndroidCodingChallengeTheme {
        Greeting("Android")
    }
}