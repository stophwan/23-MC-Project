package com.example.mc_project.db.dao

import androidx.room.*
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User
import com.example.mc_project.db.table.UserWithTastePlace

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Transaction
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserWithTastePlace() : List<UserWithTastePlace>

    @Transaction
    @Query("SELECT * FROM users where id IN (:followerIds)")
    fun getFollower(followersId: List<Int>) : List<User>
}