package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.ReadingResult
import com.pishongroup.biblelock.domain.model.ReadingSession

class ValidateReadingCompleteUseCase {
    operator fun invoke(session: ReadingSession, minDwellSeconds: Int = 45): ReadingResult {
        if (session.scrollProgress < 1.0f) return ReadingResult.Incomplete("Keep reading")
        if (session.dwellTimeSeconds < minDwellSeconds) return ReadingResult.Incomplete("Take your time")
        if (!session.recallAttempted) return ReadingResult.NeedsRecallCheck
        return if (session.recallCorrect) ReadingResult.Complete else ReadingResult.RecallFailed
    }
}