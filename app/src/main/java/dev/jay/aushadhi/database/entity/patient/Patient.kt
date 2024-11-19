package dev.jay.aushadhi.database.entity.patient

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import dev.jay.aushadhi.utils.PATIENT_INFO_COLUMN_ID
import dev.jay.aushadhi.utils.PATIENT_INFO_PATIENT_ID
import dev.jay.aushadhi.utils.VISIT_PATIENT_ID

data class Patient(
    @Embedded val patientInfo: PatientInfo,
    @Relation(
        parentColumn = PATIENT_INFO_PATIENT_ID,
        entityColumn = VISIT_PATIENT_ID
    )
    val visits: List<Visit>
)
