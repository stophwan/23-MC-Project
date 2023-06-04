package com.example.mc_project.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.mc_project.db.table.TastePlace

@Dao
interface TastePlaceDao {

    @Insert
    fun insert(tastePlace: TastePlace)

    @Update
    fun update(tastePlace: TastePlace)

    @Delete
    fun delete(tastePlace: TastePlace)
}