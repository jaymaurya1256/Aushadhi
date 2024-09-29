package dev.vedics.aushadhi.ui.screens.patient

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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.vedics.aushadhi.ui.components.AddButton
import dev.vedics.aushadhi.ui.components.AddPatientScreen
import dev.vedics.aushadhi.ui.components.BottomNavigationBar
import dev.vedics.aushadhi.ui.components.ListItemMainPatient
import dev.vedics.aushadhi.utils.ButtonType
import dev.vedics.aushadhi.utils.ScreenType

@Composable
fun PatientScreen(navController: NavController, viewModel: PatientViewModel = hiltViewModel()) {

    var bottomNavHeight by remember { mutableIntStateOf(0) }
    val patientList by viewModel.listOfPatientsInfo.collectAsState(initial = emptyList())

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
                    patientList,
                    key = { patient -> patient.id }
                )
                { item ->
                    ListItemMainPatient(
                        item.name,
                        item.patientId
                    )
                }
            }
            BottomNavigationBar(
                navController = navController,
                screenType = ScreenType.PATIENT,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned { bottomNavHeight = it.size.height }
            )

            AddButton(
                text = "Add+", category = ButtonType.ADD_PATIENT, modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(vertical = bottomNavHeight.toDp() + 8.dp)
                    .padding(16.dp)
            ) {
                navController.navigate(AddPatientScreen)
            }
        }
    }
}