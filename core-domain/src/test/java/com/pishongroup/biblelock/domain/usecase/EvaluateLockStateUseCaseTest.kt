package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.LockState
import com.pishongroup.biblelock.domain.model.LockWindow
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class EvaluateLockStateUseCaseTest {

    private val useCase = EvaluateLockStateUseCase()
    private val window = LockWindow(LocalTime.of(5, 0), LocalTime.of(9, 0))
    private val today = LocalDate.of(2026, 10, 18)

    private fun at(hour: Int, minute: Int = 0) =
        LocalDateTime.of(today, LocalTime.of(hour, minute))

    @Test
    fun `locked inside the window when not yet read`() {
        assertEquals(LockState.LOCKED, useCase(at(7), window, null, false))
    }

    @Test
    fun `reading state while reading inside the window`() {
        assertEquals(LockState.READING, useCase(at(7), window, null, true))
    }

    @Test
    fun `unlocked once today's reading is complete`() {
        assertEquals(LockState.UNLOCKED, useCase(at(7), window, today, false))
    }

    @Test
    fun `finishing yesterday does not unlock today`() {
        assertEquals(LockState.LOCKED, useCase(at(7), window, today.minusDays(1), false))
    }

    @Test
    fun `unlocked before the window starts`() {
        assertEquals(LockState.UNLOCKED, useCase(at(4, 59), window, null, false))
    }

    @Test
    fun `window start is inclusive`() {
        assertEquals(LockState.LOCKED, useCase(at(5, 0), window, null, false))
    }

    @Test
    fun `window end is exclusive`() {
        assertEquals(LockState.UNLOCKED, useCase(at(9, 0), window, null, false))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `window must start before it ends`() {
        LockWindow(LocalTime.of(9, 0), LocalTime.of(5, 0))
    }
}