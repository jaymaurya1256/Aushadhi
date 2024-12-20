package dev.jay.aushadhi.ui.screens.patient.prescription

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.jay.aushadhi.ui.components.AddVisitScreen
import dev.jay.aushadhi.ui.components.BottomBarPrescription
import dev.jay.aushadhi.utils.ErrorTypes
import dev.jay.aushadhi.utils.dpToPx
import dev.jay.aushadhi.utils.drawPaths

private const val TAG = "Prescription"

@Composable
fun AddPrescriptionScreen(
    navController: NavController,
    patientId: Long,
    viewModel: PrescriptionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    var currentPath by remember { mutableStateOf(Path()) }
    var isDrawing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.prescriptionSaveOperationResult.collect { it ->
            if (it == ErrorTypes.NO_ERROR) {
                Toast.makeText(context, "Prescription saved", Toast.LENGTH_SHORT).show()
                navController.navigate(
                    AddVisitScreen(
                        patientId = patientId,
                        prescriptionFilePaths = viewModel.savedPrescriptionImagePaths
                    )
                ) {
                    launchSingleTop = true
                    popUpTo(
                        navController.previousBackStackEntry?.destination?.id ?: return@navigate
                    ) {
                        inclusive = false
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Something went wrong...! The prescription is not saved",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            currentPath.moveTo(offset.x, offset.y)
                            isDrawing = true
                        },
                        onDrag = { change, _ ->
                            change.consume()
                            currentPath.lineTo(change.position.x, change.position.y)
                            viewModel.displayPaths.add(currentPath)
                        },
                        onDragEnd = {
                            if (isDrawing) {
                                viewModel.paths.add(viewModel.displayPaths.last())
                                viewModel.displayPaths.clear()
                                viewModel.displayPaths.addAll(viewModel.paths)
                                currentPath = Path()
                                isDrawing = false
                            }
                        }
                    )
                }
        ) {
            drawPaths(viewModel.displayPaths)
        }

        BottomBarPrescription(
            onClickClean = {
                viewModel.paths.clear()
                viewModel.displayPaths.clear()
            },
            onClickUndo = {
                try {
                    viewModel.paths.removeLast()
                    viewModel.displayPaths.clear()
                    viewModel.displayPaths.addAll(viewModel.paths)
                } catch (e: Exception) {
                    Log.e(TAG, "AddPrescriptionScreen: ", e)
                }
            },
            onClickSave = {
                viewModel.savePrescription(
                    context,
                    "prescription$patientId",
                    dpToPx(context, configuration.screenWidthDp.toFloat()),
                    dpToPx(context, configuration.screenHeightDp.toFloat())
                )
            },
            onClickPrint = {
                viewModel.printPrescription()
            }
        )
    }
}


