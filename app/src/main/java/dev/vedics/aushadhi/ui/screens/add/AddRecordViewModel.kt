package dev.vedics.aushadhi.ui.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.AushadhiDao
import dev.vedics.aushadhi.database.dao.DiseaseDao
import dev.vedics.aushadhi.database.dao.PatientsDao
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.database.entity.Disease
import dev.vedics.aushadhi.utils.ErrorTypes
import dev.vedics.aushadhi.utils.oneShotFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecordViewModel @Inject constructor(
    private val aushadhiDao: AushadhiDao,
    private val diseaseDao: DiseaseDao
) : ViewModel() {

    var name by mutableStateOf("")
    var description by mutableStateOf("")
    var errorTypes by mutableStateOf<ErrorTypes?>(null)
    val databaseOperationResult = oneShotFlow<ErrorTypes>()

    fun saveAushadhiRecords(name: String, description: String) {
        viewModelScope.launch {
            try {
                aushadhiDao.insert(Aushadhi(name = name, description = description))
                databaseOperationResult.tryEmit(ErrorTypes.NO_ERROR)
            } catch (e: Exception) {
                databaseOperationResult.tryEmit(ErrorTypes.DATABASE_ERROR)
            }
        }
    }

    fun saveDiseaseRecords(name: String, description: String) {
        viewModelScope.launch {
            try {
                diseaseDao.insert(Disease(name = name, description = description))
                databaseOperationResult.tryEmit(ErrorTypes.NO_ERROR)
            } catch (e: Exception) {
                databaseOperationResult.tryEmit(ErrorTypes.DATABASE_ERROR)
            }
        }
    }

}