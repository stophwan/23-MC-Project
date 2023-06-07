package com.example.mc_project.db.dao

import androidx.room.*
import com.example.mc_project.db.table.FriendList

@Dao
interface FriendListDao {
    @Insert
    fun insert(friend: FriendList)

    @Update
    fun update(friend: FriendList)

    @Delete
    fun delete(friend: FriendList)
}