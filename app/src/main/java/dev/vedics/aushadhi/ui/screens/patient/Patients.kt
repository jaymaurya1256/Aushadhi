package dev.vedics.aushadhi.ui.screens.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.database.entity.patient.Patient
import dev.vedics.aushadhi.database.entity.patient.PatientInfo
import dev.vedics.aushadhi.database.entity.patient.Visit
import dev.vedics.aushadhi.ui.components.AddButton
import dev.vedics.aushadhi.ui.components.BottomNavigationBar
import dev.vedics.aushadhi.ui.components.ListItemMainPatient
import dev.vedics.aushadhi.ui.screens.disease.DiseaseViewModel
import dev.vedics.aushadhi.utils.ADD_RECORD_SCREEN
import dev.vedics.aushadhi.utils.ButtonType

@Composable
fun PatientScreen(navController: NavController, viewModel: PatientViewModel = hiltViewModel()) {

    var bottomNavHeight by remember { mutableIntStateOf(0) }
    var items by remember { mutableIntStateOf(0) }
    var patientList by remember {
        mutableStateOf(
            listOf(
                PatientInfo(0, "unknown", 0)
            )
        )
    }

    LaunchedEffect(key1 = items) {
        viewModel.listOfPatientsInfo.collect {
            patientList = it
            items = patientList.size
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
                    ListItemMainPatient(
                        patientList[item].name,
                        patientList[item].patientId
                    )
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
private fun Item(title: String, itemHeight: Dp) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(itemHeight),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
