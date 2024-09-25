package dev.vedics.aushadhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import dev.vedics.aushadhi.ui.components.AppNavigation
import dev.vedics.aushadhi.ui.theme.AushadhiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AushadhiTheme {
                AppNavigation()
            }
        }
    }
}