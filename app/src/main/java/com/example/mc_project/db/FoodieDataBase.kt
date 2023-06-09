package com.example.mc_project.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mc_project.db.dao.FollowDao
import com.example.mc_project.db.dao.TastePlaceDao
import com.example.mc_project.db.dao.UserDao
import com.example.mc_project.db.table.Follow
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User
import com.example.mc_project.db.table.UserWithTastePlace

@Database(entities = [User::class, TastePlace::class, Follow::class], version = 1)
abstract class FoodieDataBase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun tastePlaceDao(): TastePlaceDao
    abstract fun followDao(): FollowDao

    companion object {
        private var instance: FoodieDataBase ?= null

        @Synchronized
        fun getInstance(context: Context) : FoodieDataBase? {
            if(instance == null) {
                synchronized(FoodieDataBase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, FoodieDataBase::class.java, "foodie-database")
                        .build()
                }
            }
            return instance
        }
    }
}