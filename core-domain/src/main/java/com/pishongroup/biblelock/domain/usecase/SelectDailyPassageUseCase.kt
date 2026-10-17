package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.Passage
import java.time.LocalDate

class SelectDailyPassageUseCase {
    operator fun invoke(passages: List<Passage>, date: LocalDate): Passage? {
        if (passages.isEmpty()) return null
        val sorted = passages.sortedBy { it.id }
        val index = date.toEpochDay().mod(sorted.size.toLong()).toInt()
        return sorted[index]
    }
}