package dev.vedics.aushadhi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.vedics.aushadhi.database.dao.AushadhiDAO
import dev.vedics.aushadhi.ui.screens.aushadhi.Aushadhi

@Database(entities = [Aushadhi::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun aushadhiDao(): AushadhiDAO

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "room_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
