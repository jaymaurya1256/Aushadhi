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
interface DiseaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(room: Aushadhi)

    @Delete
    suspend fun delete(room: Aushadhi)

    @Update
    suspend fun update(room: Aushadhi)

    @Query("SELECT * FROM aushadhi")
    suspend fun getAll(): List<Disease>
}