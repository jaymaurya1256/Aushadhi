package dev.jay.aushadhi.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.jay.aushadhi.ui.screens.add.AddRecord
import dev.jay.aushadhi.ui.screens.aushadhi.AushadhiDetail
import dev.jay.aushadhi.ui.screens.aushadhi.AushadhiScreen
import dev.jay.aushadhi.ui.screens.disease.DiseaseDetail
import dev.jay.aushadhi.ui.screens.disease.DiseaseScreen
import dev.jay.aushadhi.ui.screens.add.AddPatient
import dev.jay.aushadhi.ui.screens.patient.visits.AddVisitScreen
import dev.jay.aushadhi.ui.screens.patient.PatientDetail
import dev.jay.aushadhi.ui.screens.patient.PatientScreen
import dev.jay.aushadhi.ui.screens.patient.prescription.AddPrescriptionScreen
import dev.jay.aushadhi.ui.screens.patient.visits.ShowVisits
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

        composable<PatientDetailScreen> {
            val args = it.toRoute<PatientDetailScreen>()
            PatientDetail(navController, patientId = args.id)
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

        composable<AddVisitScreen>{
            val args = it.toRoute<AddVisitScreen>()
            AddVisitScreen(navController = navController, patientId = args.patientId, prescriptionFilePaths = args.prescriptionFilePaths)
        }

        composable<AddPrescriptionScreen> {
            val args = it.toRoute<AddPrescriptionScreen>()
            AddPrescriptionScreen(navController = navController, args.patientId)
        }

        composable<ShowVisits> {
            val args = it.toRoute<ShowVisits>()
            ShowVisits(patientId = args.patientId, visitId = args.visitId)
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
data class PatientDetailScreen(
    val id: Long
)

@Serializable
data class AddRecordScreen(
    val recordType: String
)

@Serializable
object AddPatientScreen

@Serializable
data class AddVisitScreen (
    val patientId: Long,
    val prescriptionFilePaths: List<String> = emptyList()
)

@Serializable
data class AddPrescriptionScreen(
    val patientId: Long
)

@Serializable
data class ShowVisits(
    val patientId: Long,
    val visitId: Int
)