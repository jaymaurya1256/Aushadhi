package dev.vedics.aushadhi.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.vedics.aushadhi.database.entity.Aushadhi

interface AushadhiDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(room: Aushadhi)

    @Delete
    suspend fun delete(room: Aushadhi)

    @Update
    suspend fun update(room: Aushadhi)

    @Query("SELECT * FROM aushadhi")
    suspend fun getAll(): LiveData<List<Aushadhi>>
}