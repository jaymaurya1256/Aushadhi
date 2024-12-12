package dev.jay.aushadhi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.jay.aushadhi.database.entity.patient.Patient
import dev.jay.aushadhi.database.entity.patient.PatientInfo
import dev.jay.aushadhi.database.entity.patient.Visit
import dev.jay.aushadhi.utils.PATIENT_INFO_PATIENT_ID
import dev.jay.aushadhi.utils.PATIENT_INFO_TABLE_NAME
import dev.jay.aushadhi.utils.VISITS_TABLE_NAME
import dev.jay.aushadhi.utils.VISIT_ID
import dev.jay.aushadhi.utils.VISIT_PATIENT_ID
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
    fun getPatientInfo(patientId: Long): Flow<PatientInfo>

    @Query("SELECT * FROM $VISITS_TABLE_NAME WHERE $VISIT_PATIENT_ID= :patientId")
    fun getPatientVisits(patientId: Long): Flow<List<Visit>>

    @Query("SELECT * FROM $VISITS_TABLE_NAME WHERE $VISIT_PATIENT_ID= :patientId AND $VISIT_ID= :visitId")
    fun getPatientVisitById(patientId: Long, visitId: Int): Flow<Visit>

    @Query("SELECT * FROM $PATIENT_INFO_TABLE_NAME WHERE name LIKE '%' || :query || '%'")
    fun searchPatients(query: String): Flow<List<PatientInfo>>
}