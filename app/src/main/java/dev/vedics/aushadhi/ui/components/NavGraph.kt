package dev.vedics.aushadhi.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.vedics.aushadhi.ui.screens.disease.DiseaseDetail
import dev.vedics.aushadhi.ui.screens.add.AddRecord
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiDetail
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiScreen
import dev.vedics.aushadhi.ui.screens.disease.DiseaseScreen
import dev.vedics.aushadhi.ui.screens.patient.PatientScreen
import dev.vedics.aushadhi.utils.ADD_RECORD_SCREEN
import dev.vedics.aushadhi.utils.AUSHADHI_DETAIL_ID
import dev.vedics.aushadhi.utils.AUSHADHI_DETAIL_SCREEN
import dev.vedics.aushadhi.utils.AUSHADHI_SCREEN
import dev.vedics.aushadhi.utils.DISEASE_DETAIL_ID
import dev.vedics.aushadhi.utils.DISEASE_DETAIL_SCREEN
import dev.vedics.aushadhi.utils.DISEASE_SCREEN
import dev.vedics.aushadhi.utils.PATIENT_SCREEN
import dev.vedics.aushadhi.utils.RECORD_TYPE

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

        composable(
            route = "$ADD_RECORD_SCREEN/{$RECORD_TYPE}",
            arguments = listOf(navArgument(
                RECORD_TYPE
            ) { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(RECORD_TYPE)?.let {
                AddRecord(navController, recordType = it)
            }
        }

        composable(
            route = "$AUSHADHI_DETAIL_SCREEN/{$AUSHADHI_DETAIL_ID}",
            arguments = listOf(navArgument(AUSHADHI_DETAIL_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(AUSHADHI_DETAIL_ID)
            AushadhiDetail(id = id ?: 0)
        }

        composable(
            route = "$DISEASE_DETAIL_SCREEN/{$DISEASE_DETAIL_ID}",
            arguments = listOf(navArgument(DISEASE_DETAIL_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(DISEASE_DETAIL_ID)
            DiseaseDetail(navController = navController, id = id ?: 0)
        }

    }
}
