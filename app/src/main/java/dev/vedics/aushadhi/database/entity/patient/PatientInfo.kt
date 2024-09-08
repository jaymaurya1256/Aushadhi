package dev.vedics.aushadhi.database.entity.patient

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patientInfo")
data class PatientInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val patientId: Long
)