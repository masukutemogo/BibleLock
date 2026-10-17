package com.pishongroup.biblelock.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passages")
data class PassageEntity(
    @PrimaryKey val id: String,
    val reference: String,
    val text: String,
    val recallPrompt: String,
    // Acceptable answers joined with "|" (see Mappers.kt)
    val acceptableAnswers: String
)
