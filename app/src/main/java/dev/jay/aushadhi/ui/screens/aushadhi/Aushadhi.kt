package dev.jay.aushadhi.ui.screens.aushadhi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.jay.aushadhi.ui.components.AddButton
import dev.jay.aushadhi.ui.components.AddRecordScreen
import dev.jay.aushadhi.ui.components.BottomNavigationBar
import dev.jay.aushadhi.ui.components.ListItemMain
import dev.jay.aushadhi.ui.components.SmallButtons
import dev.jay.aushadhi.utils.ButtonType
import dev.jay.aushadhi.utils.RECORD_AUSHADHI
import dev.jay.aushadhi.utils.ScreenType

private const val TAG = "Aushadhi"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AushadhiScreen(
    navController: NavController,
    viewModel: AushadhiViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var bottomNavHeight by remember { mutableIntStateOf(0) }
    val listOfAushadhi = viewModel.requiredListOfAushadhi.collectAsState(initial = emptyList())
    with(LocalDensity.current) {

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {

                AnimatedVisibility(
                    visible = showBottomSheet,
                    enter = expandVertically(animationSpec = tween(300)) + fadeIn(),
                    exit = shrinkVertically(animationSpec = tween(200)) + fadeOut()
                ) {
                    Row {
                        OutlinedTextField(
                            value = viewModel.searchQuery,
                            onValueChange = {
                                viewModel.searchQuery = it
                                viewModel.updateRequiredListOfAushadhi()},
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(5f).padding(6.dp),
                            placeholder = { Text("Search...") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                        SmallButtons(
                            text = "Search Button",
                            category = ButtonType.SEARCH,
                            modifier = Modifier.weight(1f).padding(end = 4.dp, top = 5.dp, bottom = 4.dp),
                            contentColor = Color.Black,
                            backgroundColor = Color.White
                        ) {
                            showBottomSheet = false
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
        }

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
                SmallButtons(
                    text = "Search Button", category = ButtonType.SEARCH, modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp)
                ) {
                    showBottomSheet = true
                }
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
