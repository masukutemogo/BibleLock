package com.pishongroup.biblelock.data.seed

import com.pishongroup.biblelock.domain.repository.PassageRepository

class PassageSeeder(
    private val repository: PassageRepository,
    private val readJson: () -> String
) {
    // Safe to call on every launch: saving replaces rows with the same id.
    suspend fun seed() {
        repository.savePassages(PassageJsonParser.parse(readJson()))
    }
}