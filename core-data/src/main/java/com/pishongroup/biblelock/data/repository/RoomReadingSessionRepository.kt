package com.pishongroup.biblelock.data.repository

import com.pishongroup.biblelock.data.local.ReadingSessionDao
import com.pishongroup.biblelock.data.mapper.toDomain
import com.pishongroup.biblelock.data.mapper.toEntity
import com.pishongroup.biblelock.domain.model.ReadingSession
import com.pishongroup.biblelock.domain.repository.ReadingSessionRepository

class RoomReadingSessionRepository(
    private val dao: ReadingSessionDao
) : ReadingSessionRepository {

    override suspend fun startSession(session: ReadingSession): Long =
        dao.insert(session.toEntity())

    override suspend fun updateSession(id: Long, session: ReadingSession) =
        dao.update(session.toEntity(id))

    override suspend fun getSession(id: Long): ReadingSession? =
        dao.getById(id)?.toDomain()

    override suspend fun getRecentSessions(limit: Int): List<ReadingSession> =
        dao.getRecent(limit).map { it.toDomain() }
}