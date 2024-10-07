package dev.vedics.aushadhi.ui.screens.patient

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import dev.vedics.aushadhi.database.entity.patient.Visit
import dev.vedics.aushadhi.ui.components.AddButton
import dev.vedics.aushadhi.ui.components.AddPrescriptionScreen
import dev.vedics.aushadhi.ui.theme.Orange
import dev.vedics.aushadhi.utils.ButtonType


private const val TAG = "AddVisitScreen"
@Composable
fun AddVisitScreen(
    navController: NavController,
    patientId: Long,
    viewModel: AddVisitViewModel = hiltViewModel()
) {

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
                .padding(top = 40.dp)
                .padding(16.dp)
                .captionBarPadding()
                .navigationBarsPadding()
                .background(MaterialTheme.colorScheme.secondary)
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
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
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
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
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
                    navController.navigate(AddPrescriptionScreen(patientId = patientId, visitId = 0))
                    // TODO: figure out how to pass valid visitId in above line
                },
                modifier = Modifier.fillMaxWidth(),
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
                        diagnosis = viewModel.diagnosis
                    )
                    Log.d(TAG, "AddVisitScreen: $newVisit")
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
