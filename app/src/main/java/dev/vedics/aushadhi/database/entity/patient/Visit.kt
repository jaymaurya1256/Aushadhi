package dev.vedics.aushadhi.database.entity.patient

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "visits",
    foreignKeys = [ForeignKey(
        entity = PatientInfo::class,
        parentColumns = ["id"],
        childColumns = ["patientId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["patientId"])]
)
data class Visit(
    @PrimaryKey(autoGenerate = true) val visitId: Int = 0,
    val patientId: Int, // Foreign key
    val visitDate: Long,
    val prescription: String,
    val diagnosis: String
)
