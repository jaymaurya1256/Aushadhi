package dev.vedics.aushadhi.database.entity.patient

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.vedics.aushadhi.utils.PATIENT_INFO_TABLE_NAME

@Entity(tableName = PATIENT_INFO_TABLE_NAME)
data class PatientInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val contactNumber: String,
    val age: Int,
    val gender: Gender,
    val description: String,
    val patientId: Long
)

enum class Gender {
    Male,
    Female,
    Other
}