package dev.vedics.aushadhi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vedics.aushadhi.database.MainDatabase
import dev.vedics.aushadhi.database.dao.AushadhiDAO
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
    fun provideUserDao(database: MainDatabase): AushadhiDAO {
        return database.aushadhiDao()
    }
}
