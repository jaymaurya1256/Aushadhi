package dev.jay.aushadhi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jay.aushadhi.database.dao.AushadhiDao
import dev.jay.aushadhi.database.dao.DiseaseDao
import dev.jay.aushadhi.database.dao.PatientsDao
import dev.jay.aushadhi.database.entity.Aushadhi
import dev.jay.aushadhi.database.entity.Disease
import dev.jay.aushadhi.database.entity.patient.PatientInfo
import dev.jay.aushadhi.database.entity.patient.Visit

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
