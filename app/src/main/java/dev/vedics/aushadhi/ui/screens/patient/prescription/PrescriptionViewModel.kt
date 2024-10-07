package dev.vedics.aushadhi.ui.screens.patient.prescription

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import android.graphics.Canvas as AndroidCanvas
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PrescriptionViewModel @Inject constructor() : ViewModel() {
    val paths = mutableStateListOf<Path>()
    val displayPaths = mutableStateListOf<Path>()

    fun savePrescription(context: Context, filename: String, width: Int, height: Int) {
        val bitmap = toBitmap(width, height)
        saveBitmapToFile(context, filename, bitmap)
    }
    fun printPrescription() {
        // TODO: Print prescription
    }

    private fun toBitmap(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val androidCanvas = AndroidCanvas(bitmap)
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            style = android.graphics.Paint.Style.STROKE
            strokeWidth = 4f
        }
        paths.forEach { path ->
            androidCanvas.drawPath(path.asAndroidPath(), paint)
        }
        return bitmap
    }

    private fun saveBitmapToFile(context: Context, filename: String, bitmap: Bitmap): String {
        val directory = context.filesDir
        val file = File(directory, "$filename.png")

        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream) // Save as PNG
            fileOutputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fileOutputStream?.close()
        }

        return file.absolutePath // Return the saved file path
    }

}