package dev.vedics.aushadhi.ui.screens.patient.prescription

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrescriptionViewModel @Inject constructor() : ViewModel() {
    val paths = mutableStateListOf<Path>()
    val displayPaths = mutableStateListOf<Path>()

    fun savePrescription() {
        // TODO: Save prescription to database
    }
    fun printPrescription() {
        // TODO: Print prescription
    }
}