package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.LockState
import com.pishongroup.biblelock.domain.model.LockWindow
import java.time.LocalDate
import java.time.LocalDateTime

class EvaluateLockStateUseCase {
    operator fun invoke(
        now: LocalDateTime,
        window: LockWindow,
        lastCompletedDate: LocalDate?,
        readingInProgress: Boolean
    ): LockState {
        if (lastCompletedDate == now.toLocalDate()) return LockState.UNLOCKED
        if (!window.contains(now.toLocalTime())) return LockState.UNLOCKED
        return if (readingInProgress) LockState.READING else LockState.LOCKED
    }
}