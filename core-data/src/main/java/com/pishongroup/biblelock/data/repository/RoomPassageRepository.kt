package com.pishongroup.biblelock.data.repository

import com.pishongroup.biblelock.data.local.PassageDao
import com.pishongroup.biblelock.data.mapper.toDomain
import com.pishongroup.biblelock.data.mapper.toEntity
import com.pishongroup.biblelock.domain.model.Passage
import com.pishongroup.biblelock.domain.repository.PassageRepository

class RoomPassageRepository(private val dao: PassageDao) : PassageRepository {
    override suspend fun getPassage(id: String): Passage? = dao.getById(id)?.toDomain()
    override suspend fun getAllPassages(): List<Passage> = dao.getAll().map { it.toDomain() }
    override suspend fun savePassages(passages: List<Passage>) =
        dao.insertAll(passages.map { it.toEntity() })
    override suspend fun countPassages(): Int = dao.count()
}