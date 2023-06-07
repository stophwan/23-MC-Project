package com.example.mc_project.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FriendList (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val followingId: Int,
    @ColumnInfo val followerId: Int,
)