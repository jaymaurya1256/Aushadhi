package dev.vedics.aushadhi.ui.screens.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.vedics.aushadhi.database.entity.patient.Visit
import dev.vedics.aushadhi.ui.theme.Orange

@Composable
fun AddVisitScreen(navController: NavController, patientId: Long, viewModel: AddVisitViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.patientId = patientId
    }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Orange,
            secondary = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "Add Visit",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            TextField(
                value = viewModel.prescription,
                onValueChange = { viewModel.prescription = it },
                label = { Text("Prescription") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            TextField(
                value = viewModel.diagnosis,
                onValueChange = { viewModel.diagnosis = it },
                label = { Text("Diagnosis") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    val newVisit = Visit(
                        patientId = viewModel.patientId,
                        visitDate = viewModel.visitDate,
                        prescription = viewModel.prescription,
                        diagnosis = viewModel.diagnosis
                    )
                    viewModel.addVisit(newVisit)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Visit", fontSize = 18.sp)
            }
        }
    }
}
