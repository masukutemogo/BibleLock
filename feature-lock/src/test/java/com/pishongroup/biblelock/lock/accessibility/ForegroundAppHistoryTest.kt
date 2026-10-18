package com.pishongroup.biblelock.lock.accessibility

import org.junit.Assert.assertEquals
import org.junit.Test

class ForegroundAppHistoryTest {

    @Test
    fun `newest app comes first`() {
        val history = ForegroundAppHistory()
        history.record("a")
        history.record("b")
        assertEquals(listOf("b", "a"), history.recent.value)
    }

    @Test
    fun `consecutive duplicates are ignored`() {
        val history = ForegroundAppHistory()
        history.record("a")
        history.record("a")
        assertEquals(listOf("a"), history.recent.value)
    }

    @Test
    fun `history is capped at max size`() {
        val history = ForegroundAppHistory(maxSize = 3)
        listOf("a", "b", "c", "d").forEach(history::record)
        assertEquals(listOf("d", "c", "b"), history.recent.value)
    }
}