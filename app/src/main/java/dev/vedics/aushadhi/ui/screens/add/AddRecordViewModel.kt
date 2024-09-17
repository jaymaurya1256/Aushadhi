package dev.vedics.aushadhi.ui.screens.add

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.AushadhiDao
import dev.vedics.aushadhi.database.entity.Aushadhi
import javax.inject.Inject

@HiltViewModel
class AddRecordViewModel @Inject constructor(private val aushadhiDao: AushadhiDao): ViewModel() {
    suspend fun saveRecords(aushadhiName: String, description: String) {
        aushadhiDao.insert(Aushadhi(name = aushadhiName, description = description))
    }
}