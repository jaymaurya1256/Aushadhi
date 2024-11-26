package dev.jay.aushadhi.ui.screens.aushadhi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.jay.aushadhi.ui.components.AddButton
import dev.jay.aushadhi.ui.components.AddRecordScreen
import dev.jay.aushadhi.ui.components.BottomNavigationBar
import dev.jay.aushadhi.ui.components.ListItemMain
import dev.jay.aushadhi.utils.ButtonType
import dev.jay.aushadhi.utils.RECORD_AUSHADHI
import dev.jay.aushadhi.utils.ScreenType

private const val TAG = "Aushadhi"

@Composable
fun AushadhiScreen(
    navController: NavController,
    viewModel: AushadhiViewModel = hiltViewModel()
) {
    var bottomNavHeight by remember { mutableIntStateOf(0) }
    val listOfAushadhi = viewModel.requiredListOfAushadhi.collectAsState(initial = emptyList())
    with(LocalDensity.current) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    top = WindowInsets.systemBars.getTop(this@with).toDp(),
                    bottom = bottomNavHeight.toDp()
                )
            ) {
                items(
                    listOfAushadhi.value,
                    key = { aushadhi -> aushadhi.id }
                ) { item ->
                    ListItemMain(
                        item.id,
                        item.name,
                        item.description,
                        navController,
                        screenType = ScreenType.AUSHADHI
                    )
                }
            }

            BottomNavigationBar(
                navController = navController,
                screenType = ScreenType.AUSHADHI,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned { bottomNavHeight = it.size.height }
            )

            Column(
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(vertical = bottomNavHeight.toDp() + 8.dp)
            ) {
                AddButton(
                    text = "Add+", category = ButtonType.ADD_AUSHADHI, modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp)
                ) {
                    navController.navigate(AddRecordScreen(RECORD_AUSHADHI)) {
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewDynamicCardList() {
    val navController = rememberNavController()
    AushadhiScreen(navController = navController)
}