package dev.vedics.aushadhi.ui.screens.patient

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.vedics.aushadhi.R
import dev.vedics.aushadhi.ui.components.AddButton
import dev.vedics.aushadhi.ui.components.AddVisitScreen
import dev.vedics.aushadhi.ui.components.DisplayImage
import dev.vedics.aushadhi.ui.components.ShowVisits
import dev.vedics.aushadhi.utils.ButtonType
import dev.vedics.aushadhi.utils.timeInMilliToDate

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
            .navigationBarsPadding()
            .captionBarPadding()
            .padding(top = 32.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Patient Details",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                patientDetail?.let { patient ->
                    Text(
                        text = "Name: ${patient.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.Serif
                    )
                    Text(
                        text = "Age: ${patient.age}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.Serif
                    )
                    Text(
                        text = "Gender: ${patient.gender}",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Visits",
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(modifier = Modifier.weight(1f), contentPadding = PaddingValues(bottom = 64.dp)) {
                        items(visits) { visit ->
                            Card(
                                modifier = Modifier.padding(4.dp).fillMaxHeight(0.3f),
                                colors = CardColors(
                                    contentColor = Color.Black,
                                    containerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    disabledContentColor = Color.Black
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                onClick = {
                                    navController.navigate(ShowVisits(patientId = patientId, visitId = visit.visitId)) {
                                        launchSingleTop = true
                                    }
                                }
                            ) {
                                Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                                    Column(modifier = Modifier
                                        .fillMaxSize()
                                        .weight(3f)) {
                                        Text(
                                            text = timeInMilliToDate(visit.visitDate),
                                            fontWeight = FontWeight.SemiBold,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = "Notes: ${visit.diagnosis}",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }

                                    VerticalDivider(modifier = Modifier.weight(0.1f))

                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                            .align(Alignment.Bottom)
                                    ) {
                                        if (visit.prescriptionImagePaths.isNotEmpty()) {
                                            val listOfPaths = visit.prescriptionImagePaths.removeSurrounding("[","]").split(", ").map { it.trim() }
                                            if (listOfPaths.isNotEmpty()) {
                                                DisplayImage(
                                                    filePath = listOfPaths[0],
                                                    sampleImageRes = R.drawable.prescription
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

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
    Box(modifier = Modifier.fillMaxSize()) {
        AddButton(
            text = "Add Visit",
            onClick = {
                patientDetail?.let { patient ->
                    navController.navigate(AddVisitScreen(patient.patientId))
                }
            },
            category = ButtonType.ADD_VISIT,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp).navigationBarsPadding()
        )
    }
}

