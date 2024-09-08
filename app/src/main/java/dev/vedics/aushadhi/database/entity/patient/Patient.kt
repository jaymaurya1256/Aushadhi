package dev.vedics.aushadhi.database.entity.patient

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class Patient(
    @Embedded val patientInfo: PatientInfo,
    @Relation(
        parentColumn = "id",
        entityColumn = "patientId"
    )
    val visits: List<Visit>
)
