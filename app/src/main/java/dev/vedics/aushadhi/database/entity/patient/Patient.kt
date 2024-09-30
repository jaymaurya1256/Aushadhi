package dev.vedics.aushadhi.database.entity.patient

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import dev.vedics.aushadhi.utils.PATIENT_INFO_COLUMN_ID
import dev.vedics.aushadhi.utils.PATIENT_INFO_PATIENT_ID
import dev.vedics.aushadhi.utils.VISIT_PATIENT_ID

data class Patient(
    @Embedded val patientInfo: PatientInfo,
    @Relation(
        parentColumn = PATIENT_INFO_PATIENT_ID,
        entityColumn = VISIT_PATIENT_ID
    )
    val visits: List<Visit>
)
