package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.RecallQuestion
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CheckRecallAnswerUseCaseTest {

    private val useCase = CheckRecallAnswerUseCase()
    private val question = RecallQuestion("What is Jesus called in John 15?", listOf("vine", "true vine"))

    @Test
    fun `correct answer ignoring case and punctuation`() {
        assertTrue(useCase(question, "The VINE!"))
    }

    @Test
    fun `wrong answer rejected`() {
        assertFalse(useCase(question, "a shepherd"))
    }

    @Test
    fun `blank answer rejected`() {
        assertFalse(useCase(question, "   "))
    }
}