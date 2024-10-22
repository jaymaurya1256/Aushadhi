package dev.vedics.aushadhi.database.entity.patient

import androidx.compose.ui.graphics.Path
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.vedics.aushadhi.utils.PATIENT_INFO_COLUMN_ID
import dev.vedics.aushadhi.utils.PATIENT_INFO_PATIENT_ID
import dev.vedics.aushadhi.utils.VISITS_TABLE_NAME
import dev.vedics.aushadhi.utils.VISIT_PATIENT_ID

@Entity(
    tableName = VISITS_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = PatientInfo::class,
        parentColumns = [PATIENT_INFO_PATIENT_ID],
        childColumns = [VISIT_PATIENT_ID],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = [VISIT_PATIENT_ID])]
)
data class Visit(
    @PrimaryKey(autoGenerate = true) val visitId: Int = 0,
    val patientId: Long, // Foreign key
    val visitDate: Long,
    val prescription: String,
    val diagnosis: String,
    val prescriptionImagePaths: String = "",
)
