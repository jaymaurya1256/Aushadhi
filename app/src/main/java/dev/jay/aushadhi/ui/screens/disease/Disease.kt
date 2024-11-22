package dev.jay.aushadhi.ui.screens.disease

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.jay.aushadhi.ui.components.AddButton
import dev.jay.aushadhi.ui.components.AddRecordScreen
import dev.jay.aushadhi.ui.components.BottomNavigationBar
import dev.jay.aushadhi.ui.components.ListItemMain
import dev.jay.aushadhi.utils.ButtonType
import dev.jay.aushadhi.utils.RECORD_DISEASE
import dev.jay.aushadhi.utils.ScreenType

@Composable
fun DiseaseScreen(
    navController: NavController,
    viewModel: DiseaseViewModel = hiltViewModel()
) {

    var bottomNavHeight by remember { mutableIntStateOf(0) }
    val diseaseList = viewModel.listOfDisease.collectAsState(initial = emptyList())
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
                    diseaseList.value,
                    key = { disease -> disease.id }
                ) { item ->
                    ListItemMain(
                        item.id,
                        item.name,
                        item.description,
                        navController,
                        ScreenType.DISEASE
                    )
                }
            }
            BottomNavigationBar(
                navController = navController,
                screenType = ScreenType.DISEASE,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned { bottomNavHeight = it.size.height })

            AddButton(
                text = "Add+",
                category = ButtonType.ADD_DISEASE,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(vertical = bottomNavHeight.toDp() + 8.dp)
                    .padding(16.dp)
            ) {
                navController.navigate(AddRecordScreen(RECORD_DISEASE)) {
                    launchSingleTop = true
                }
            }
        }
    }
}
