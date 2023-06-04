package com.example.mc_project.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.mc_project.db.table.Friend

@Dao
interface FriendDao {
    @Insert
    fun insert(friend: Friend)

    @Update
    fun update(friend: Friend)

    @Delete
    fun delete(friend: Friend)
}