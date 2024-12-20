package dev.jay.aushadhi.ui.screens.disease

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.DiseaseDao
import dev.jay.aushadhi.database.entity.Disease
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DiseaseViewModel @Inject constructor(private val diseaseDao: DiseaseDao): ViewModel() {
    var name by mutableStateOf("")
    var description by mutableStateOf("")

    val listOfDisease = diseaseDao.getAll()

    fun getDiseaseById(id: Int): Flow<Disease> = diseaseDao.getDiseaseById(id)
}