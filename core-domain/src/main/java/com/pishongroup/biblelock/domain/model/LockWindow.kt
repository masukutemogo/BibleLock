package com.pishongroup.biblelock.domain.model

import java.time.LocalTime

data class LockWindow(val start: LocalTime, val end: LocalTime) {
    init {
        require(start < end) { "Lock window must start before it ends (overnight windows are not supported)" }
    }

    // Start is inclusive, end is exclusive
    fun contains(time: LocalTime): Boolean = time >= start && time < end
}