package dev.vedics.aushadhi.ui.screens.aushadhi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.ui.components.AddButton
import dev.vedics.aushadhi.ui.components.BottomNavigationBar
import dev.vedics.aushadhi.ui.screens.add.AddRecordViewModel
import dev.vedics.aushadhi.utils.ADD_RECORD_SCREEN
import dev.vedics.aushadhi.utils.ButtonType

private const val TAG = "Aushadhi"

@Composable
fun AushadhiScreen(
    navController: NavController,
    viewModel: AushadhiViewModel = hiltViewModel()
) {

    var bottomNavHeight by remember { mutableIntStateOf(0) }
    var items by remember { mutableIntStateOf(0) }
    var aushadhiList by remember {
        mutableStateOf(listOf(Aushadhi(0, "", "")))
    }

    LaunchedEffect(key1 = items) {
        viewModel.listOfAushadhi.collect {
            aushadhiList = it
            items = it.size
        }
    }
    with(LocalDensity.current) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentPadding = PaddingValues(
                    top = WindowInsets.systemBars.getTop(this@with).toDp(),
                    bottom = bottomNavHeight.toDp()
                )
            ) {
                items(items) { item ->
                    Item(aushadhiList[item].name, aushadhiList[item].description)
                }
            }

            BottomNavigationBar(
                navController = navController,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned { bottomNavHeight = it.size.height }
            )

            AddButton(
                text = "Add+", category = ButtonType.ADD_AUSHADHI, modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(vertical = bottomNavHeight.toDp() + 8.dp)
                    .padding(16.dp)
            ) {
                navController.navigate(ADD_RECORD_SCREEN)
            }
        }
    }
}

@Composable
private fun Item(title: String, description: String) {
    Card(
        modifier = Modifier
            .padding(top = 6.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Cursive
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewDynamicCardList() {
    val sampleItems = List(20) { "Item #$it" }
    val navController = rememberNavController()
    AushadhiScreen(navController = navController)
}
