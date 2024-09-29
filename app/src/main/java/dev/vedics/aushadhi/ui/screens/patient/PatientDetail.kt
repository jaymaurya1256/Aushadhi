package dev.vedics.aushadhi.ui.screens.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.vedics.aushadhi.ui.components.AddButton
import dev.vedics.aushadhi.ui.components.AddVisitScreen
import dev.vedics.aushadhi.utils.ButtonType

@Composable
fun PatientDetail(
    navController: NavController,
    patientId: Long,
    viewModel: PatientDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.patientDetail = viewModel.getPatientInfo(patientId = patientId)
        viewModel.visits = viewModel.getPatientVisit(patientId = patientId)
    }
    val patientDetail by viewModel.patientDetail.collectAsState(initial = null)
    val visits by viewModel.visits.collectAsState(initial = emptyList())

    Card(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .captionBarPadding()
            .padding(top = 32.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Patient Details",
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            patientDetail?.let { patient ->
                Text(text = "Name: ${patient.name}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Serif)
                Text(text = "Age: ${patient.age}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Serif)
                Text(text = "Gender: ${patient.gender}", style = MaterialTheme.typography.bodySmall, fontFamily = FontFamily.Serif)
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Visits", style = MaterialTheme.typography.bodyLarge, fontFamily = FontFamily.Serif)
                visits.forEach { visit ->
                    Text(
                        text = "Visit Date: ${visit.visitDate}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Notes: ${visit.diagnosis}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                }
                AddButton(
                    text = "Add Visit",
                    onClick = {
                        navController.navigate(AddVisitScreen(patient.patientId))
                    },
                    category = ButtonType.ADD_VISIT
                )
            } ?: run {
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}

