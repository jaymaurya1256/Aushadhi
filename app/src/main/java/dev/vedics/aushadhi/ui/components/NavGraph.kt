package dev.vedics.aushadhi.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.vedics.aushadhi.ui.screens.add.AddRecord
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiDetail
import dev.vedics.aushadhi.ui.screens.aushadhi.AushadhiScreen
import dev.vedics.aushadhi.ui.screens.disease.DiseaseDetail
import dev.vedics.aushadhi.ui.screens.disease.DiseaseScreen
import dev.vedics.aushadhi.ui.screens.patient.AddPatient
import dev.vedics.aushadhi.ui.screens.patient.PatientScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AushadhiScreen,
    ) {
        composable<AushadhiScreen> {
            AushadhiScreen(navController)
        }

        composable<PatientScreen> {
            PatientScreen(navController)
        }

        composable<DiseaseScreen> {
            DiseaseScreen(navController)
        }

        composable<AddRecordScreen>{
            val args = it.toRoute<AddRecordScreen>()
            AddRecord(navController = navController, recordType = args.recordType)
        }

        composable<AddPatientScreen>{
            AddPatient(navController = navController)
        }

        composable<AushadhiDetailScreen>{
            val args = it.toRoute<AushadhiDetailScreen>()
            AushadhiDetail(id = args.id)
        }

        composable<DiseaseDetailScreen>{
            val args = it.toRoute<DiseaseDetailScreen>()
            DiseaseDetail(navController = navController, id = args.id)
        }
    }
}

@Serializable
object AushadhiScreen

@Serializable
data class AushadhiDetailScreen(
    val id: Int
)

@Serializable
object DiseaseScreen

@Serializable
data class DiseaseDetailScreen(
    val id: Int
)

@Serializable
object PatientScreen

@Serializable
object PatientDetailScreen

@Serializable
data class AddRecordScreen(
    val recordType: String
)

@Serializable
object AddPatientScreen
