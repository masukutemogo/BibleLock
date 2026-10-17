package com.pishongroup.biblelock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReadingSessionDao {
    @Insert
    suspend fun insert(session: ReadingSessionEntity): Long

    @Update
    suspend fun update(session: ReadingSessionEntity)

    @Query("SELECT * FROM reading_sessions WHERE id = :id")
    suspend fun getById(id: Long): ReadingSessionEntity?

    @Query("SELECT * FROM reading_sessions ORDER BY startedAtEpochMs DESC LIMIT :limit")
    suspend fun getRecent(limit: Int): List<ReadingSessionEntity>
}