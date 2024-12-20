package dev.jay.aushadhi.ui.screens.aushadhi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.AushadhiDao
import javax.inject.Inject

@HiltViewModel
class AushadhiViewModel @Inject constructor(private val aushadhiDao: AushadhiDao) : ViewModel() {
    var name by mutableStateOf("")
    var description by  mutableStateOf("")
    var searchQuery by mutableStateOf("")
    private val listOfAushadhi = aushadhiDao.getAll()
    var requiredListOfAushadhi = listOfAushadhi

    suspend fun fetchAndSaveAushadhiById(id: Int) {
        val aushadhiDetail = aushadhiDao.getAushadhiById(id)
        aushadhiDetail.collect {
            name = it.name
            description = it.description
        }
    }
}