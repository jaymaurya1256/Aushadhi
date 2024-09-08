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

@Dao
interface PatientsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(patientInfo: PatientInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisit(visit: Visit)

    @Delete
    suspend fun delete(patientInfo: PatientInfo)

    @Update
    suspend fun update(patientInfo: PatientInfo)

    @Query("SELECT * FROM patientInfo")
    suspend fun getAll(): List<PatientInfo>

    @Query("SELECT * FROM patientInfo WHERE patientId = :patientId")
    suspend fun getPatientInfo(patientId: Long): Patient

    @Query("SELECT * FROM visits WHERE patientId= :patientId")
    suspend fun getPatientVisit(patientId: Long): List<Visit>
}