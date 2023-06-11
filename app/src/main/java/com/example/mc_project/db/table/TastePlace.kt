package com.example.mc_project.db.table

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("userId")
    ]
)
data class TastePlace (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo val userId: Int,
    @ColumnInfo var type: String,
    @ColumnInfo var longitude: Double,
    @ColumnInfo var latitude: Double,
    @ColumnInfo var name: String,
    @ColumnInfo var rate: Double,
    @ColumnInfo var content: String
)