package dev.vedics.aushadhi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.vedics.aushadhi.database.entity.Aushadhi
import dev.vedics.aushadhi.database.entity.Disease

@Dao
interface DiseaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(disease: Aushadhi)

    @Delete
    suspend fun delete(disease: Aushadhi)

    @Update
    suspend fun update(disease: Aushadhi)

    @Query("SELECT * FROM disease")
    suspend fun getAll(): List<Disease>
}