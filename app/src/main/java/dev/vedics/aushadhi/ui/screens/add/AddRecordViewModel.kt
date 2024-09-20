package dev.vedics.aushadhi.ui.screens.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.AushadhiDao
import dev.vedics.aushadhi.database.dao.DiseaseDao
import dev.vedics.aushadhi.database.dao.PatientsDao
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.database.entity.Disease
import javax.inject.Inject

@HiltViewModel
class AddRecordViewModel @Inject constructor(
    private val aushadhiDao: AushadhiDao,
    private val diseaseDao: DiseaseDao
) : ViewModel() {
    suspend fun saveAushadhiRecords(name: String, description: String) {
        aushadhiDao.insert(Aushadhi(name = name, description = description))
    }

    suspend fun saveDiseaseRecords(name: String, description: String) {
        diseaseDao.insert(Disease(name = name, description = description))
    }

}