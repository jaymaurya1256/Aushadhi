package dev.vedics.aushadhi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.vedics.aushadhi.database.entity.Aushadhi

@Dao
interface AushadhiDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(aushadhi: Aushadhi)

    @Delete
    suspend fun delete(aushadhi: Aushadhi)

    @Update
    suspend fun update(aushadhi: Aushadhi)

    @Query("SELECT * FROM aushadhi")
    suspend fun getAll(): List<Aushadhi>
}