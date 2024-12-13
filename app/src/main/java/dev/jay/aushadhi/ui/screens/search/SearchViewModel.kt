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
            ) { aushadhis, diseases, patients ->
                buildList {
                    // Add Aushadhi results
                    aushadhis.forEach { aushadhiItem ->
                        add(
                            SearchResult(
                                id = aushadhiItem.id.toLong(),
                                name = aushadhiItem.name,
                                description = aushadhiItem.description,
                                type = Type.AUSHADHI
                            )
                        )
                    }

                    // Add Disease results
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

                    // Add Patient results
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
            }.flowOn(Dispatchers.IO) // Ensure database operations run on a background thread
                .catch { e ->
                    // Handle any errors, e.g., log or show a message
                    e.printStackTrace()
                }
                .collect { combinedResults ->
                    searchResults.value = combinedResults
                }
        }
    }
}
