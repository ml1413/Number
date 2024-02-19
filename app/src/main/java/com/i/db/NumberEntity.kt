package com.i.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class NumberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val text: String,
    val number: String
)