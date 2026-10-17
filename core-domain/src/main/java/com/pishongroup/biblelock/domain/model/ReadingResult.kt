package com.pishongroup.biblelock.domain.model

sealed class ReadingResult {
    data class Incomplete(val reason: String) : ReadingResult()
    data object NeedsRecallCheck : ReadingResult()
    data object RecallFailed : ReadingResult()
    data object Complete : ReadingResult()
}