package com.example.mc_project.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @ColumnInfo(name = "authId") var authId: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "taste_cnt") var tasteCount: Int
){
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
