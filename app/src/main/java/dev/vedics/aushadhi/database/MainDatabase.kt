package dev.vedics.aushadhi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.vedics.aushadhi.database.dao.AushadhiDao
import dev.vedics.aushadhi.database.dao.DiseaseDao
import dev.vedics.aushadhi.database.dao.PatientsDao
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.database.entity.Disease
import dev.vedics.aushadhi.database.entity.patient.PatientInfo
import dev.vedics.aushadhi.database.entity.patient.Visit

@Database(
    entities = [Aushadhi::class, Disease::class, PatientInfo::class, Visit::class],
    version = 1,
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun aushadhiDao(): AushadhiDao
    abstract fun diseaseDao(): DiseaseDao
    abstract fun patientDao(): PatientsDao
}
