package dev.jay.aushadhi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.jay.aushadhi.database.entity.Aushadhi
import dev.jay.aushadhi.database.entity.Disease
import kotlinx.coroutines.flow.Flow

@Dao
interface DiseaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(disease: Disease)

    @Delete
    suspend fun delete(disease: Disease)

    @Update
    suspend fun update(disease: Disease)

    @Query("SELECT * FROM disease")
    fun getAll(): Flow<List<Disease>>

    @Query("SELECT * FROM disease WHERE id = :id")
    fun getDiseaseById(id: Int): Flow<Disease>

    @Query("SELECT * FROM disease WHERE name LIKE '%' || :query || '%'")
    fun searchDiseases(query: String): Flow<List<Disease>>
}