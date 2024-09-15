package dev.vedics.aushadhi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vedics.aushadhi.database.MainDatabase
import dev.vedics.aushadhi.database.dao.AushadhiDao
import dev.vedics.aushadhi.database.dao.DiseaseDao
import dev.vedics.aushadhi.database.dao.PatientsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "main_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideAushadhiDao(database: MainDatabase): AushadhiDao {
        return database.aushadhiDao()
    }

    @Provides
    @Singleton
    fun provideDiseaseDao(database: MainDatabase): DiseaseDao {
        return database.diseaseDao()
    }

    @Provides
    fun providePatientDao(database: MainDatabase): PatientsDao {
        return database.patientDao()
    }

}
