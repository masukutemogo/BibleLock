package com.pishongroup.biblelock.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_sessions")
data class ReadingSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val passageId: String,
    val startedAtEpochMs: Long,
    val scrollProgress: Float,
    val dwellTimeSeconds: Int,
    val recallAttempted: Boolean,
    val recallCorrect: Boolean
)