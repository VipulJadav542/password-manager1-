package com.rk.mynote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")

data class Notes(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    val title: String,
    val content: String,
    val creation_date: String,
    val last_modified_date: String,
    val pined_icon:Int
)

