package com.pishongroup.biblelock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PassageDao {
    @Query("SELECT * FROM passages WHERE id = :id")
    suspend fun getById(id: String): PassageEntity?

    @Query("SELECT * FROM passages ORDER BY id")
    suspend fun getAll(): List<PassageEntity>

    @Query("SELECT COUNT(*) FROM passages")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(passages: List<PassageEntity>)
}