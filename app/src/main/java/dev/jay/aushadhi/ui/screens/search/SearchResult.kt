package dev.jay.aushadhi.ui.screens.search

data class SearchResult(
    val id: Long,
    val name: String,
    val description: String,
    val type: Type
)

enum class Type {
    AUSHADHI,
    DISEASE,
    PATIENT
}