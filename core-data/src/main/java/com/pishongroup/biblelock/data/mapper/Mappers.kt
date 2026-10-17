package com.pishongroup.biblelock.data.mapper

import com.pishongroup.biblelock.data.local.PassageEntity
import com.pishongroup.biblelock.data.local.ReadingSessionEntity
import com.pishongroup.biblelock.domain.model.Passage
import com.pishongroup.biblelock.domain.model.ReadingSession
import com.pishongroup.biblelock.domain.model.RecallQuestion
import java.time.Instant

private const val ANSWER_SEPARATOR = "|"

fun PassageEntity.toDomain() = Passage(
    id = id,
    reference = reference,
    text = text,
    recallQuestion = RecallQuestion(
        prompt = recallPrompt,
        acceptableAnswers = acceptableAnswers
            .split(ANSWER_SEPARATOR)
            .filter { it.isNotBlank() }
    )
)

fun Passage.toEntity() = PassageEntity(
    id = id,
    reference = reference,
    text = text,
    recallPrompt = recallQuestion.prompt,
    acceptableAnswers = recallQuestion.acceptableAnswers.joinToString(ANSWER_SEPARATOR)
)

fun ReadingSessionEntity.toDomain() = ReadingSession(
    passageId = passageId,
    startedAt = Instant.ofEpochMilli(startedAtEpochMs),
    scrollProgress = scrollProgress,
    dwellTimeSeconds = dwellTimeSeconds,
    recallAttempted = recallAttempted,
    recallCorrect = recallCorrect
)

fun ReadingSession.toEntity(id: Long = 0) = ReadingSessionEntity(
    id = id,
    passageId = passageId,
    startedAtEpochMs = startedAt.toEpochMilli(),
    scrollProgress = scrollProgress,
    dwellTimeSeconds = dwellTimeSeconds,
    recallAttempted = recallAttempted,
    recallCorrect = recallCorrect
)