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
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TastePlace (
    @ColumnInfo val userId: Int,
    @ColumnInfo var rate: Double,
    @ColumnInfo var content: String
){
    @PrimaryKey(autoGenerate = true) val id: Int = 0
}