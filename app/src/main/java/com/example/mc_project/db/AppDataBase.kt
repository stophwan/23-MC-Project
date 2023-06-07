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

@Database(entities = [User::class, TastePlace::class, Follow::class], version = 1)
abstract class FoodieDataBase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun tastePlaceDao(): TastePlaceDao
    abstract fun followDao(): FollowDao

    companion object {
        @Volatile private var instance: FoodieDataBase ?= null

        fun getInstance(context: Context) : FoodieDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) : FoodieDataBase{
            return Room.databaseBuilder(context.applicationContext, FoodieDataBase::class.java, "Foodie").build();
        }
    }
}