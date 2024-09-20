package dev.vedics.aushadhi.ui.screens.aushadhi

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vedics.aushadhi.database.dao.AushadhiDao
import dev.vedics.aushadhi.database.entity.Aushadhi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AushadhiViewModel @Inject constructor(private val aushadhiDao: AushadhiDao) : ViewModel() {
    val listOfAushadhi = aushadhiDao.getAll()

    fun getAushadhiById(id: Int): Flow<Aushadhi> = aushadhiDao.getAushadhiById(id)
}