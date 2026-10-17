package com.pishongroup.biblelock.data.mapper

import com.pishongroup.biblelock.domain.model.Passage
import com.pishongroup.biblelock.domain.model.ReadingSession
import com.pishongroup.biblelock.domain.model.RecallQuestion
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant

class MappersTest {

    @Test
    fun `passage survives a round trip through the entity`() {
        val passage = Passage(
            id = "john-15",
            reference = "John 15:1-8",
            text = "I am the true vine...",
            recallQuestion = RecallQuestion("What is Jesus called?", listOf("vine", "true vine"))
        )
        assertEquals(passage, passage.toEntity().toDomain())
    }

    @Test
    fun `reading session survives a round trip through the entity`() {
        val session = ReadingSession(
            passageId = "john-15",
            startedAt = Instant.ofEpochMilli(1_700_000_000_000),
            scrollProgress = 1f,
            dwellTimeSeconds = 60,
            recallAttempted = true,
            recallCorrect = true
        )
        assertEquals(session, session.toEntity().toDomain())
    }
}