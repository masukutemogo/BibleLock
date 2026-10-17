package com.pishongroup.biblelock.domain.repository

import com.pishongroup.biblelock.domain.model.ReadingSession

interface ReadingSessionRepository {
    suspend fun startSession(session: ReadingSession): Long
    suspend fun updateSession(id: Long, session: ReadingSession)
    suspend fun getSession(id: Long): ReadingSession?
    suspend fun getRecentSessions(limit: Int): List<ReadingSession>
}