package dev.vedics.aushadhi.ui.screens.patient

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.PatientsDao
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val patientsDao: PatientsDao): ViewModel() {
    val listOfPatientsInfo = patientsDao.getAll()
}