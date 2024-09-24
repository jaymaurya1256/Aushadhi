package dev.vedics.aushadhi.ui.screens.aushadhi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.AushadhiDao
import dev.vedics.aushadhi.database.entity.Aushadhi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AushadhiViewModel @Inject constructor(private val aushadhiDao: AushadhiDao) : ViewModel() {
    val name = mutableStateOf("")
    val description =  mutableStateOf("")


    val listOfAushadhi = aushadhiDao.getAll()


    fun getAushadhiById(id: Int): Flow<Aushadhi> = aushadhiDao.getAushadhiById(id)
}