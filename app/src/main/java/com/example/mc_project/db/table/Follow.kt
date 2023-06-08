package com.example.mc_project.db.table

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["followingId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["followerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index("followerId"),
        Index("followingId")
    ]
)
data class Follow (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo val followingId: Int,
    @ColumnInfo val followerId: Int,

)