package com.example.mc_project.db.dao

import androidx.room.*
import com.example.mc_project.db.table.TastePlace

@Dao
interface TastePlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tastePlace: TastePlace)

    @Update
    fun update(tastePlace: TastePlace)

    @Delete
    fun delete(tastePlace: TastePlace)
}