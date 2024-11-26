package dev.jay.aushadhi.ui.screens.patient.visits

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jay.aushadhi.R
import dev.jay.aushadhi.ui.components.DisplayImage
import dev.jay.aushadhi.utils.timeInMilliToDate

private const val TAG = "ShowVisits"
@Composable
fun ShowVisits(patientId: Long, visitId: Int, viewModel: ShowVisitsViewModel = hiltViewModel()) {
    var parentWidthDp by remember { mutableStateOf(0.dp) }
    val visitDetail = viewModel.getParticularVisit(patientId, visitId).collectAsState(initial = null)
    val dateFormatted = visitDetail.value?.visitDate?.let { timeInMilliToDate(it) }
    val density = LocalDensity.current
    LaunchedEffect(Unit) {
        viewModel.getParticularVisit(patientId, visitId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .onGloballyPositioned { layoutCoordinates ->
                val widthInPx = layoutCoordinates.size.width
                with(density) {
                    parentWidthDp = widthInPx.toDp()
                }
            }
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Visit Details",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Patient ID: ${visitDetail.value?.patientId}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Visit Date: $dateFormatted",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Diagnosis: ${visitDetail.value?.diagnosis}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Prescription: ${visitDetail.value?.prescription}",
                    style = MaterialTheme.typography.bodyMedium
                )

                if (!visitDetail.value?.prescriptionImagePaths.isNullOrEmpty()) {
                    val listOfPrescriptions = visitDetail.value?.prescriptionImagePaths?.removeSurrounding("[","]")?.split(", ")?.map { it.trim() }
                    listOfPrescriptions?.let {
                        LazyColumn {
                            items(it) { item ->
                                DisplayImage(filePath = item, R.drawable.prescription, size = parentWidthDp)
                                HorizontalDivider()
                            }
                        }
                    }

                }
            }
        }
    }
}