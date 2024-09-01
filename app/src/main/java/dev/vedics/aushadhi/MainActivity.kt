package dev.vedics.aushadhi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiList
import dev.vedics.aushadhi.ui.theme.AushadhiTheme

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
                            IconButton(onClick = { /*TODO*/ }) {
                                Image(painter = painterResource(id = R.drawable.drug), contentDescription = "Aushadhi")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Image(painter = painterResource(id = R.drawable.disease), contentDescription = "Rog")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painter = painterResource(id = R.drawable.patient), contentDescription = "Patient")
                            }
                            Spacer(modifier = Modifier.weight(0.5f))
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Spacer(modifier = Modifier.padding(innerPadding))
                    AushadhiList(items = List(20) { "Item #$it" })
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AushadhiPreview() {
    AushadhiList(items = List(20) { "Item #$it" })
}