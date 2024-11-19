package dev.jay.aushadhi.ui.screens.patient.visits

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.PatientsDao
import dev.jay.aushadhi.database.entity.patient.Visit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

private const val TAG = "ShowVisitsViewModel"
@HiltViewModel
class ShowVisitsViewModel @Inject constructor(private val patientDao: PatientsDao) : ViewModel() {

    fun getParticularVisit(patientId: Long, visitId: Int): Flow<Visit> {
        return patientDao.getPatientVisitById(patientId, visitId)
    }
}