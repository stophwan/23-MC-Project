package com.example.mc_project.db.dao

import androidx.room.*
import com.example.mc_project.db.table.Follow

@Dao
interface FollowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(friend: Follow)

    @Update
    fun update(friend: Follow)

    @Delete
    fun delete(friend: Follow)

    @Transaction
    @Query("SELECT * FROM follow where followingId = :id")
    fun getFollowerList(id: Int) : List<Follow>


    @Transaction
    @Query("DELETE FROM follow where id = :id")
    fun deleteFollow(id: Int)
}