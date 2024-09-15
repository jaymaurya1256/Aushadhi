package dev.vedics.aushadhi.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disease")
data class Disease(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String
)
