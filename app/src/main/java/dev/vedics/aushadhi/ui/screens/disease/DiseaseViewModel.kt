package dev.vedics.aushadhi.ui.screens.disease

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.DiseaseDao
import javax.inject.Inject

@HiltViewModel
class DiseaseViewModel @Inject constructor(diseaseDao: DiseaseDao): ViewModel() {
    val listOfDisease = diseaseDao.getAll()
}