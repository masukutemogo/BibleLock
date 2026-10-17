package com.pishongroup.biblelock.domain.repository

import com.pishongroup.biblelock.domain.model.Passage

interface PassageRepository {
    suspend fun getPassage(id: String): Passage?
    suspend fun getAllPassages(): List<Passage>
    suspend fun savePassages(passages: List<Passage>)
    suspend fun countPassages(): Int
}