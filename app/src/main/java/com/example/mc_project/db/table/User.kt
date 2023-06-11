package com.example.mc_project.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var authId: String,
    @ColumnInfo var password: String,
    @ColumnInfo var name: String,
    @ColumnInfo var tasteCount: Int,
    @ColumnInfo var friendCount: Int
)
