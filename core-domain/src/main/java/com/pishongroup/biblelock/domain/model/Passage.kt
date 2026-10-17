package com.pishongroup.biblelock.domain.model

data class RecallQuestion(
    val prompt: String,
    val acceptableAnswers: List<String>
)

data class Passage(
    val id: String,
    val reference: String,
    val text: String,
    val recallQuestion: RecallQuestion
)