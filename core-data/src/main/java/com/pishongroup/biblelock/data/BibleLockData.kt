package com.pishongroup.biblelock.data

import android.content.Context
import androidx.room.Room
import com.pishongroup.biblelock.data.local.BibleLockDatabase
import com.pishongroup.biblelock.data.repository.RoomPassageRepository
import com.pishongroup.biblelock.data.repository.RoomReadingSessionRepository
import com.pishongroup.biblelock.data.seed.PassageSeeder
import com.pishongroup.biblelock.data.seed.readPassagesAsset
import com.pishongroup.biblelock.domain.repository.PassageRepository
import com.pishongroup.biblelock.domain.repository.ReadingSessionRepository

class BibleLockData(context: Context) {
    private val appContext = context.applicationContext

    private val database = Room.databaseBuilder(
        appContext,
        BibleLockDatabase::class.java,
        "biblelock.db"
    ).build()

    val passageRepository: PassageRepository =
        RoomPassageRepository(database.passageDao())

    val readingSessionRepository: ReadingSessionRepository =
        RoomReadingSessionRepository(database.readingSessionDao())

    val passageSeeder = PassageSeeder(passageRepository) { appContext.readPassagesAsset() }
}