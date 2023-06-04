package com.example.mc_project.db.dao

import androidx.room.*
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query(
        "SELECT * FROM user" +
                "JOIN taste_place ON user.id = taste_place.user_id" +
                "WHERE user.id = id"
    )
    fun loadUserAndTastePlaces(id: Int): Map<User, List<TastePlace>>
}