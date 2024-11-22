package dev.jay.aushadhi.database.entity.patient

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.jay.aushadhi.utils.PATIENT_INFO_TABLE_NAME

@Entity(tableName = PATIENT_INFO_TABLE_NAME)
data class PatientInfo(
    @PrimaryKey
    val patientId: Long,
    val name: String,
    val contactNumber: String,
    val age: Int,
    val gender: Gender,
    val description: String
)

enum class Gender {
    Male,
    Female,
    Other
}