package dev.jay.aushadhi.ui.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.PatientsDao
import dev.jay.aushadhi.database.entity.patient.Gender
import dev.jay.aushadhi.database.entity.patient.PatientInfo
import dev.jay.aushadhi.utils.ErrorTypes
import dev.jay.aushadhi.utils.oneShotFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(private val patientDao: PatientsDao) : ViewModel() {

    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var age by mutableStateOf(0)
    var contactNumber by mutableStateOf("")
    var errorTypes by mutableStateOf<ErrorTypes?>(null)
    val databaseOperationResult = oneShotFlow<ErrorTypes>()

    fun savePatientRecords(name: String, age: Int, description: String) {
        viewModelScope.launch {
            try {
                patientDao.insert(
                    PatientInfo(
                        name = name,
                        contactNumber = contactNumber,
                        age = age,
                        gender = Gender.Male,
                        description = description,
                        patientId = System.currentTimeMillis()
                    )
                )
                databaseOperationResult.tryEmit(ErrorTypes.NO_ERROR)
            } catch (e: Exception) {
                databaseOperationResult.tryEmit(ErrorTypes.DATABASE_ERROR)
            }
        }
    }
}