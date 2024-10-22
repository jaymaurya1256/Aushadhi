package dev.vedics.aushadhi.ui.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import dev.vedics.aushadhi.ui.components.PatientScreen
import dev.vedics.aushadhi.ui.theme.Orange
import dev.vedics.aushadhi.utils.ErrorTypes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPatient(
    navController: NavController,
    viewModel: AddPatientViewModel = hiltViewModel()
) {
    val snackBar = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        viewModel.databaseOperationResult.collect { it ->
            if (it == ErrorTypes.NO_ERROR) {
                navController.navigate(PatientScreen)
            } else {
                snackBar.showSnackbar("Error saving patient record")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Patient") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Orange, titleContentColor = Color.White
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBar) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFF0F0F0))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    label = { Text("Patient Name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
                    ),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    ),
                    isError = viewModel.errorTypes == ErrorTypes.NAME_EMPTY
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = viewModel.contactNumber,
                    onValueChange = { viewModel.contactNumber = it },
                    label = { Text("Contact Number") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next, keyboardType = KeyboardType.Phone
                    ),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    ),
                    isError = viewModel.errorTypes == ErrorTypes.CONTACT_EMPTY
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = viewModel.age.toString(),
                    onValueChange = { viewModel.age = it.toIntOrNull() ?: 0 },
                    label = { Text("Patient Age") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
                    ),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    ),
                    isError = viewModel.errorTypes == ErrorTypes.AGE_EMPTY
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    label = { Text("Patient Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done, keyboardType = KeyboardType.Text
                    ),
                    maxLines = 6,
                    visualTransformation = VisualTransformation.None,
                    isError = viewModel.errorTypes == ErrorTypes.DESCRIPTION_EMPTY
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (viewModel.name.isEmpty() || viewModel.age == 0 || viewModel.description.isEmpty()) {
                            viewModel.errorTypes = when {
                                viewModel.name.isEmpty() -> ErrorTypes.NAME_EMPTY
                                viewModel.age == 0 -> ErrorTypes.AGE_EMPTY
                                viewModel.description.isEmpty() -> ErrorTypes.DESCRIPTION_EMPTY
                                viewModel.contactNumber.isEmpty() -> ErrorTypes.CONTACT_EMPTY
                                else -> ErrorTypes.NO_ERROR
                            }
                        } else {
                            viewModel.savePatientRecords(
                                name = viewModel.name,
                                age = viewModel.age,
                                description = viewModel.description
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Save", fontSize = 18.sp)
                }
            }
        }
    )
}