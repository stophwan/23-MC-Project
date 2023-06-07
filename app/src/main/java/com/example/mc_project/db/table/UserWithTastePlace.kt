package com.example.mc_project.db.table

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTastePlace(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val tastePlaces: List<TastePlace>
)
