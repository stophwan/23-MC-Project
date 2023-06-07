package com.example.mc_project.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mc_project.db.dao.FriendListDao
import com.example.mc_project.db.dao.TastePlaceDao
import com.example.mc_project.db.dao.UserDao
import com.example.mc_project.db.table.FriendList
import com.example.mc_project.db.table.TastePlace
import com.example.mc_project.db.table.User

@Database(entities = [User::class, TastePlace::class, FriendList::class], version = 1)
abstract class FoodieDataBase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun tastePlaceDao(): TastePlaceDao
    abstract fun friendListDao(): FriendListDao

    companion object {
        private var instance: FoodieDataBase ?= null

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