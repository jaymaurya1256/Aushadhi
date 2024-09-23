package dev.vedics.aushadhi.ui.screens.add

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import dev.vedics.aushadhi.ui.components.AushadhiScreen
import dev.vedics.aushadhi.ui.components.DiseaseScreen
import dev.vedics.aushadhi.ui.theme.Orange
import dev.vedics.aushadhi.utils.AUSHADHI_SCREEN
import dev.vedics.aushadhi.utils.DISEASE_SCREEN
import dev.vedics.aushadhi.utils.ErrorTypes
import dev.vedics.aushadhi.utils.RECORD_AUSHADHI
import dev.vedics.aushadhi.utils.RECORD_DISEASE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecord(
    navController: NavController,
    recordType: String,
    viewModel: AddRecordViewModel = hiltViewModel()

) {

    LaunchedEffect(key1 = viewModel.databaseOperationResult) {
        viewModel.databaseOperationResult.collect { it ->
            if (it == ErrorTypes.NO_ERROR) {
                when (recordType) {
                    RECORD_AUSHADHI -> navController.navigate(AushadhiScreen)
                    RECORD_DISEASE -> navController.navigate(DiseaseScreen)
                }
            }else {
                //TODO: figure out how to show error message here in non-activity class
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add $recordType") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Orange, titleContentColor = Color.White
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF0F0F0))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    label = { Text("$recordType Name") },
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
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                    label = { Text("$recordType Description") },
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
                        if (viewModel.name.isEmpty() || viewModel.description.isEmpty()) {
                            viewModel.errorTypes = when {
                                viewModel.name.isEmpty() -> ErrorTypes.NAME_EMPTY
                                viewModel.description.isEmpty() -> ErrorTypes.DESCRIPTION_EMPTY
                                else -> ErrorTypes.NO_ERROR
                            }
                        } else {
                            when (recordType) {
                                RECORD_AUSHADHI -> {
                                    viewModel.saveAushadhiRecords(
                                        name = viewModel.name, description = viewModel.description
                                    )
                                }

                                RECORD_DISEASE -> {
                                    viewModel.saveDiseaseRecords(
                                        name = viewModel.name, description = viewModel.description
                                    )
                                }
                            }
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
