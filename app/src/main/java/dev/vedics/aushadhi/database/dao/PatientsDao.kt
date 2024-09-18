package dev.vedics.aushadhi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.vedics.aushadhi.database.entity.patient.Patient
import dev.vedics.aushadhi.database.entity.patient.PatientInfo
import dev.vedics.aushadhi.database.entity.patient.Visit
import dev.vedics.aushadhi.utils.PATIENT_INFO_PATIENT_ID
import dev.vedics.aushadhi.utils.PATIENT_INFO_TABLE_NAME
import dev.vedics.aushadhi.utils.VISITS_TABLE_NAME
import dev.vedics.aushadhi.utils.VISIT_PATIENT_ID
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(patientInfo: PatientInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisit(visit: Visit)

    @Delete
    suspend fun delete(patientInfo: PatientInfo)

    @Update
    suspend fun update(patientInfo: PatientInfo)

    @Query("SELECT * FROM $PATIENT_INFO_TABLE_NAME")
    fun getAll(): Flow<List<PatientInfo>>

    @Query("SELECT * FROM $PATIENT_INFO_TABLE_NAME WHERE $PATIENT_INFO_PATIENT_ID = :patientId")
    suspend fun getPatientInfo(patientId: Long): Patient

    @Query("SELECT * FROM $VISITS_TABLE_NAME WHERE $VISIT_PATIENT_ID= :patientId")
    fun getPatientVisit(patientId: Long): Flow<List<Visit>>
}