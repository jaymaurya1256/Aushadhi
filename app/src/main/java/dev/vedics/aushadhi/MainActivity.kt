package dev.vedics.aushadhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.vedics.aushadhi.ui.components.AddButton
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiScreen
import dev.vedics.aushadhi.ui.screens.patient.PatientScreen
import dev.vedics.aushadhi.ui.theme.AushadhiTheme
import dev.vedics.aushadhi.ui.theme.Orange
import dev.vedics.aushadhi.utils.ButtonType

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AushadhiTheme {
                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            Spacer(modifier = Modifier.weight(0.5f))
                            IconButton(onClick = {  }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.aushadhi),
                                    contentDescription = "Aushadhi",
                                    tint = Orange
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.disease),
                                    contentDescription = "Rog",
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.patient),
                                    contentDescription = "Patient",
                                    tint = Orange
                                )
                            }
                            Spacer(modifier = Modifier.weight(0.5f))
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {  }) {
                            AddButton(text = "Add+", category = ButtonType.ADD_AUSHADHI) {
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Spacer(modifier = Modifier.padding(innerPadding))
                    App(innerPadding)
                }
            }
        }
    }
}

@Composable
fun App(padding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "aushadhi", Modifier.padding(padding)) {
        composable(route = "aushadhi") {
            AushadhiScreen(items = List(20) { "Item #$it aushadhi" }, 100.dp)
        }
        composable(route = "disease") {
            PatientScreen(items = List(20) { "Item #$it disease" }, 100.dp)
        }
        composable(route = "patient") {
            PatientScreen(items = List(20) { "Item #$it patient" }, 100.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AushadhiPreview() {
    AushadhiScreen(items = List(20) { "Item #$it" }, 100.dp)
}