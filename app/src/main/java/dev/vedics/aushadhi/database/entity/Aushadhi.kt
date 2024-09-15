package dev.vedics.aushadhi.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aushadhi")
data class Aushadhi(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String = ""
)
