package dev.jay.aushadhi.ui.screens.patient

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.PatientsDao
import dev.jay.aushadhi.database.entity.patient.PatientInfo
import dev.jay.aushadhi.database.entity.patient.Visit
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class PatientDetailViewModel @Inject constructor(private val patientsDao: PatientsDao) : ViewModel() {
    var patientDetail = flowOf<PatientInfo>()
    var visits = flowOf<List<Visit>>()

    fun getPatientInfo(patientId: Long) = patientsDao.getPatientInfo(patientId)
    fun getPatientVisit(patientId: Long) = patientsDao.getPatientVisits(patientId)
}