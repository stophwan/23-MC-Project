package com.example.mc_project.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["followingId, followerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
class Follow (
    @ColumnInfo val followingId: Int,
    @ColumnInfo val followerId: Int,
){
    @PrimaryKey(autoGenerate = true) val id: Int = 0
}