package dev.vedics.aushadhi.ui.screens.patient.prescription

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vedics.aushadhi.ui.components.BottomBarPrescription


@Composable
fun AddPrescriptionScreen(viewModel: PrescriptionViewModel = hiltViewModel()) {
    var currentPath by remember { mutableStateOf(Path()) }
    var isDrawing by remember { mutableStateOf(false) }

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
                        },
                        onDragEnd = {
                            if (isDrawing) {
                                viewModel.paths.add(currentPath)
                                currentPath = Path()
                                isDrawing = false
                            }
                        }
                    )
                }
        ) {
            drawPaths(viewModel.paths)
            drawPath(currentPath, Color.Black)
        }

        BottomBarPrescription(
            onClickClean = {
                viewModel.paths.clear()
            },
            onClickSave = {
                viewModel.savePrescription()
            },
            onClickPrint = {
                viewModel.printPrescription()
            }
        )
    }
}

fun DrawScope.drawPaths(paths: List<Path>) {
    paths.forEach { path ->
        drawPath(path, Color.Black, style = Stroke(4f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHandwrittenNotesScreen() {
    AddPrescriptionScreen()
}