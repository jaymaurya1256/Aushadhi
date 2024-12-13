package dev.jay.aushadhi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.jay.aushadhi.database.entity.Aushadhi
import kotlinx.coroutines.flow.Flow

@Dao
interface AushadhiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(aushadhi: Aushadhi)

    @Delete
    suspend fun delete(aushadhi: Aushadhi)

    @Update
    suspend fun update(aushadhi: Aushadhi)

    @Query("SELECT * FROM aushadhi")
    fun getAll(): Flow<List<Aushadhi>>

    @Query("SELECT * FROM aushadhi WHERE id = :id")
    fun getAushadhiById(id: Int): Flow<Aushadhi>

    @Query("SELECT * FROM aushadhi WHERE name LIKE '%' || :query || '%' LIMIT 3")
    fun searchAushadhies(query: String): Flow<List<Aushadhi>>
}