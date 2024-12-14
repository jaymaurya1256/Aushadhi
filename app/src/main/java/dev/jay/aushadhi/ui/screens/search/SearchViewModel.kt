package dev.jay.aushadhi.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jay.aushadhi.database.dao.AushadhiDao
import dev.jay.aushadhi.database.dao.DiseaseDao
import dev.jay.aushadhi.database.dao.PatientsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val aushadhiDao: AushadhiDao,
    private val diseaseDao: DiseaseDao,
    private val patientsDao: PatientsDao
) : ViewModel() {

    val searchResults = MutableStateFlow<List<SearchResult>>(emptyList())

    fun search(query: String) {
        viewModelScope.launch {
            combine(
                aushadhiDao.searchAushadhies(query),
                diseaseDao.searchDiseases(query),
                patientsDao.searchPatients(query)
            ) { aushadhies, diseases, patients ->
                buildList {
                    aushadhies.forEach { aushadhiItem ->
                        add(
                            SearchResult(
                                id = aushadhiItem.id.toLong(),
                                name = aushadhiItem.name,
                                description = aushadhiItem.description,
                                type = Type.AUSHADHI
                            )
                        )
                    }

                    diseases.forEach { diseaseItem ->
                        add(
                            SearchResult(
                                id = diseaseItem.id.toLong(),
                                name = diseaseItem.name,
                                description = diseaseItem.description,
                                type = Type.DISEASE
                            )
                        )
                    }

                    patients.forEach { patientItem ->
                        add(
                            SearchResult(
                                id = patientItem.patientId,
                                name = patientItem.name,
                                description = patientItem.description,
                                type = Type.PATIENT
                            )
                        )
                    }
                }
            }.flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { combinedResults ->
                    searchResults.value = combinedResults
                }
        }
    }
}
