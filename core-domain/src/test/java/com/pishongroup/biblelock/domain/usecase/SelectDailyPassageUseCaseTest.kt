package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.Passage
import com.pishongroup.biblelock.domain.model.RecallQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.LocalDate

class SelectDailyPassageUseCaseTest {

    private val useCase = SelectDailyPassageUseCase()
    private val day = LocalDate.of(2026, 10, 17)

    private fun passage(id: String) =
        Passage(id, id, "text", RecallQuestion("question", listOf("answer")))

    @Test
    fun `returns null when there are no passages`() {
        assertNull(useCase(emptyList(), day))
    }

    @Test
    fun `same date always gives the same passage`() {
        val passages = listOf(passage("a"), passage("b"), passage("c"))
        assertEquals(useCase(passages, day), useCase(passages, day))
    }

    @Test
    fun `consecutive days cycle through all passages`() {
        val passages = listOf(passage("a"), passage("b"), passage("c"))
        val first = useCase(passages, day)
        val second = useCase(passages, day.plusDays(1))
        val third = useCase(passages, day.plusDays(2))
        val fourth = useCase(passages, day.plusDays(3))

        assertNotEquals(first, second)
        assertNotEquals(second, third)
        assertNotEquals(first, third)
        assertEquals(first, fourth)
    }

    @Test
    fun `input order does not change the result`() {
        val a = listOf(passage("a"), passage("b"), passage("c"))
        assertEquals(useCase(a, day), useCase(a.reversed(), day))
    }
}