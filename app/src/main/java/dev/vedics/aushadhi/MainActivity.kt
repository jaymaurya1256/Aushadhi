package dev.vedics.aushadhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.ui.components.Add
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiList
import dev.vedics.aushadhi.ui.screens.disease.DiseaseList
import dev.vedics.aushadhi.ui.screens.patient.PatientList
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
                                Image(
                                    painter = painterResource(id = R.drawable.aushadhi),
                                    contentDescription = "Aushadhi",
                                    colorFilter = ColorFilter.tint(Orange)
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.disease),
                                    contentDescription = "Rog",
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.patient),
                                    contentDescription = "Patient",
                                    colorFilter = ColorFilter.tint(Orange)
                                )
                            }
                            Spacer(modifier = Modifier.weight(0.5f))
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Spacer(modifier = Modifier.padding(innerPadding))
                    App()
                    Box(modifier = Modifier.fillMaxSize()) {
                        Add(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(innerPadding)
                                .padding(end = 4.dp, bottom = 4.dp),
                            text = "Add +",
                            category = ButtonType.ADD_AUSHADHI
                        ) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "aushadhi") {
        composable(route = "aushadhi") {
            AushadhiList(items = List(20) { "Item #$it aushadhi" })
        }
        composable(route = "disease") {
            DiseaseList(items = List(20) { "Item #$it disease" })
        }
        composable(route = "patient") {
            PatientList(items = List(20) { "Item #$it patient" })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AushadhiPreview() {
    AushadhiList(items = List(20) { "Item #$it" })
}