package com.pishongroup.biblelock.data.seed

import com.pishongroup.biblelock.domain.model.Passage
import com.pishongroup.biblelock.domain.repository.PassageRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PassageSeederTest {

    private class FakePassageRepository : PassageRepository {
        val stored = mutableMapOf<String, Passage>()
        override suspend fun getPassage(id: String) = stored[id]
        override suspend fun getAllPassages() = stored.values.toList()
        override suspend fun savePassages(passages: List<Passage>) {
            passages.forEach { stored[it.id] = it }
        }
        override suspend fun countPassages() = stored.size
    }

    private val json = """
        [
          {"id": "a", "reference": "A 1:1", "text": "alpha", "recallPrompt": "q", "acceptableAnswers": ["alpha"]},
          {"id": "b", "reference": "B 1:1", "text": "beta", "recallPrompt": "q", "acceptableAnswers": ["beta"]}
        ]
    """.trimIndent()

    @Test
    fun `seed saves every passage`() = runBlocking {
        val repo = FakePassageRepository()
        PassageSeeder(repo) { json }.seed()
        assertEquals(2, repo.countPassages())
    }

    @Test
    fun `seeding twice does not duplicate passages`() = runBlocking {
        val repo = FakePassageRepository()
        val seeder = PassageSeeder(repo) { json }
        seeder.seed()
        seeder.seed()
        assertEquals(2, repo.countPassages())
    }
}