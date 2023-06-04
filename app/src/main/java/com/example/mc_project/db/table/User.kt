package com.example.mc_project.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "authId") var authId: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "taste_cnt") var tasteCount: Int
)