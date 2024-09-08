package dev.vedics.aushadhi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.vedics.aushadhi.database.dao.AushadhiDAO
import dev.vedics.aushadhi.database.dao.DiseaseDAO
import dev.vedics.aushadhi.database.dao.PatientsDAO
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.database.entity.Disease
import dev.vedics.aushadhi.database.entity.patient.Patient
import dev.vedics.aushadhi.database.entity.patient.PatientInfo
import dev.vedics.aushadhi.database.entity.patient.Visit

@Database(
    entities = [Aushadhi::class, Disease::class, PatientInfo::class, Visit::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun aushadhiDao(): AushadhiDAO
    abstract fun diseaseDao(): DiseaseDAO
    abstract fun patientDao(): PatientsDAO
}
