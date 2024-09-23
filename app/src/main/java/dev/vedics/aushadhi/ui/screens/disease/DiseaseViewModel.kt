package dev.vedics.aushadhi.ui.screens.disease

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.DiseaseDao
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.database.entity.Disease
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DiseaseViewModel @Inject constructor(private val diseaseDao: DiseaseDao): ViewModel() {
    var diseaseList by mutableStateOf(listOf(Disease(0, "", "")))

    val name = mutableStateOf("")
    val description =  mutableStateOf("")

    val listOfDisease = diseaseDao.getAll()

    fun getDiseaseById(id: Int): Flow<Disease> = diseaseDao.getDiseaseById(id)
}