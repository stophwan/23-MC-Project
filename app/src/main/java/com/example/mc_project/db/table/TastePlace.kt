package com.example.mc_project.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TastePlace (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val userId: Int,
    @ColumnInfo var rate: Double,
    @ColumnInfo var content: String

)