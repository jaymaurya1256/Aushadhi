package dev.jay.aushadhi.ui.screens.aushadhi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.AushadhiDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AushadhiViewModel @Inject constructor(private val aushadhiDao: AushadhiDao) : ViewModel() {
    var name by mutableStateOf("")
    var description by  mutableStateOf("")
    var searchQuery by mutableStateOf("")
    private val listOfAushadhi = aushadhiDao.getAll()
    var requiredListOfAushadhi = listOfAushadhi

    fun updateRequiredListOfAushadhi() {
        requiredListOfAushadhi = listOfAushadhi.map { list ->
            if (searchQuery.isEmpty()) list
            else list.filter { it.name.contains(searchQuery, ignoreCase = true) }
        }
    }

    suspend fun fetchAndSaveAushadhiById(id: Int) {
        val aushadhiDetail = aushadhiDao.getAushadhiById(id)
        aushadhiDetail.collect {
            name = it.name
            description = it.description
        }
    }
}