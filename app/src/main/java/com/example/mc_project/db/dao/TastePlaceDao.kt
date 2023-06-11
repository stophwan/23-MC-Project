package com.example.mc_project.db.dao

import androidx.room.*
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User

@Dao
interface TastePlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tastePlace: TastePlace)

    @Update
    fun update(tastePlace: TastePlace)

    @Delete
    fun delete(tastePlace: TastePlace)

    @Transaction
    @Query("SELECT * FROM TastePlace where id=:id")
    fun getTastePlace(id: Int) : TastePlace
}