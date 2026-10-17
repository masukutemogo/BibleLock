package com.pishongroup.biblelock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PassageEntity::class, ReadingSessionEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BibleLockDatabase : RoomDatabase() {
    abstract fun passageDao(): PassageDao
    abstract fun readingSessionDao(): ReadingSessionDao
}