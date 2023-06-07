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

    @Query("SELECT follower_id FROM follow where following_id = :id")
    fun getFollowerList(id: Int) : List<Int>
}