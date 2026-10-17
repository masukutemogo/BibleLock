package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.ReadingResult
import com.pishongroup.biblelock.domain.model.ReadingSession
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Instant

class ValidateReadingCompleteUseCaseTest {

    private val useCase = ValidateReadingCompleteUseCase()
    private fun session(
        scroll: Float = 1f,
        dwell: Int = 60,
        attempted: Boolean = false,
        correct: Boolean = false
    ) = ReadingSession("john-15", Instant.now(), scroll, dwell, attempted, correct)

    @Test
    fun `incomplete when not fully scrolled`() {
        assertTrue(useCase(session(scroll = 0.6f)) is ReadingResult.Incomplete)
    }

    @Test
    fun `incomplete when dwell time too short`() {
        assertTrue(useCase(session(dwell = 10)) is ReadingResult.Incomplete)
    }

    @Test
    fun `needs recall check after scroll and dwell met`() {
        assertEquals(ReadingResult.NeedsRecallCheck, useCase(session()))
    }

    @Test
    fun `recall failed when answer wrong`() {
        assertEquals(ReadingResult.RecallFailed, useCase(session(attempted = true, correct = false)))
    }

    @Test
    fun `complete when everything satisfied`() {
        assertEquals(ReadingResult.Complete, useCase(session(attempted = true, correct = true)))
    }
}