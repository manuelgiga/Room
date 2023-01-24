package com.giga.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")

data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String,
    val completed: Boolean
)