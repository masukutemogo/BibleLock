package com.pishongroup.biblelock.domain.usecase

import com.pishongroup.biblelock.domain.model.RecallQuestion

class CheckRecallAnswerUseCase {
    operator fun invoke(question: RecallQuestion, userAnswer: String): Boolean {
        val normalized = normalize(userAnswer)
        if (normalized.isBlank()) return false
        return question.acceptableAnswers.any { keyword ->
            val k = normalize(keyword)
            k.isNotBlank() && normalized.contains(k)
        }
    }

    private fun normalize(text: String): String =
        text.lowercase().replace(Regex("[^a-z0-9\\s]"), "").trim()
}