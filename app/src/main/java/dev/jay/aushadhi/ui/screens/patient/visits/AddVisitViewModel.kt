package dev.jay.aushadhi.ui.screens.patient.visits

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.PatientsDao
import dev.jay.aushadhi.database.entity.patient.Visit
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddVisitViewModel @Inject constructor(private val patientsDao: PatientsDao) : ViewModel() {

    var patientId by mutableLongStateOf(0)
    var visitDate by mutableLongStateOf(System.currentTimeMillis())
    var prescription by mutableStateOf("")
    var diagnosis by mutableStateOf("")
    var imagePath = mutableStateListOf<String?>(null)

    fun addVisit(visit: Visit) {
        viewModelScope.launch {
            patientsDao.insertVisit(
                visit
            )
        }
    }
    fun addPaths(listOfPaths: List<String>) {
        listOfPaths.map { imagePath.add(it) }
    }
}