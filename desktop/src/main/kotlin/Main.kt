import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cmaina.fotos.shared.presentation.screens.app.App

fun main() {
    return application {
        Window(onCloseRequest = ::exitApplication, title = "Fotos"){
            App()
        }
    }
}