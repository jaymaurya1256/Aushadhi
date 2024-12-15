package dev.jay.aushadhi.ui.screens.patient.visits

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.jay.aushadhi.database.entity.patient.Visit
import dev.jay.aushadhi.ui.components.AddButton
import dev.jay.aushadhi.ui.components.AddPrescriptionScreen
import dev.jay.aushadhi.ui.theme.Orange
import dev.jay.aushadhi.ui.theme.White
import dev.jay.aushadhi.utils.ButtonType


private const val TAG = "AddVisitScreen"

@Composable
fun AddVisitScreen(
    navController: NavController,
    patientId: Long,
    prescriptionFilePaths: List<String> = emptyList(),
    viewModel: AddVisitViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.patientId = patientId
        viewModel.addPaths(prescriptionFilePaths)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .padding(16.dp)
            .captionBarPadding()
            .navigationBarsPadding()
    ) {
        Text(
            text = "Add Visit",
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        OutlinedTextField(
            value = viewModel.prescription,
            onValueChange = { viewModel.prescription = it },
            label = { Text("Prescription") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Default,
                keyboardType = KeyboardType.Text
            ),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            ),
            maxLines = 6,
            visualTransformation = VisualTransformation.None
        )

        OutlinedTextField(
            value = viewModel.diagnosis,
            onValueChange = { viewModel.diagnosis = it },
            label = { Text("Diagnosis") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Default,
                keyboardType = KeyboardType.Text
            ),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            ),
            maxLines = 6,
            visualTransformation = VisualTransformation.None
        )

        AddButton(
            onClick = {
                navController.navigate(AddPrescriptionScreen(patientId = patientId))
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "Write a prescription",
            category = ButtonType.ADD_PRESCRIPTION
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val newVisit = Visit(
                    patientId = viewModel.patientId,
                    visitDate = viewModel.visitDate,
                    prescription = viewModel.prescription,
                    diagnosis = viewModel.diagnosis,
                    prescriptionImagePaths = viewModel.imagePath.toList().filterNotNull().toString()
                )
                Log.d(TAG, "AddVisitScreen: $newVisit")
                viewModel.addVisit(newVisit)
                navController.popBackStack()
            },
            colors = ButtonColors(Orange, Color.White, Orange, Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Visit", fontSize = 18.sp)
        }
    }
}
