package dev.vedics.aushadhi.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vedics.aushadhi.ui.screens.add.AddRecord
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiScreen
import dev.vedics.aushadhi.ui.screens.disease.DiseaseScreen
import dev.vedics.aushadhi.ui.screens.patient.PatientScreen
import dev.vedics.aushadhi.utils.ADD_RECORD_SCREEN
import dev.vedics.aushadhi.utils.AUSHADHI_SCREEN
import dev.vedics.aushadhi.utils.DISEASE_SCREEN
import dev.vedics.aushadhi.utils.PATIENT_SCREEN

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AUSHADHI_SCREEN,
    ) {
        composable(route = AUSHADHI_SCREEN) {
            AushadhiScreen(navController)
        }
        composable(route = PATIENT_SCREEN) {
            PatientScreen(navController)
        }
        composable(route = DISEASE_SCREEN) {
            DiseaseScreen(navController)
        }
        composable(route = ADD_RECORD_SCREEN) {
            AddRecord(navController)
        }
    }
}
