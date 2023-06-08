package com.example.mc_project.db.dao

import androidx.room.*
import com.example.mc_project.db.table.Follow

@Dao
interface FollowDao {
    @Insert
    fun insert(friend: Follow)

    @Update
    fun update(friend: Follow)

    @Delete
    fun delete(friend: Follow)

    @Query("SELECT * FROM follow where followingId = :id")
    fun getFollowerList(id: Int) : List<Follow>
}