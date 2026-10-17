package com.pishongroup.biblelock.domain.model

import java.time.Instant

data class ReadingSession(
    val passageId: String,
    val startedAt: Instant,
    val scrollProgress: Float = 0f,
    val dwellTimeSeconds: Int = 0,
    val recallAttempted: Boolean = false,
    val recallCorrect: Boolean = false
)