package com.pishongroup.biblelock.data.seed

import com.pishongroup.biblelock.domain.model.Passage
import com.pishongroup.biblelock.domain.model.RecallQuestion
import org.json.JSONArray

object PassageJsonParser {
    fun parse(json: String): List<Passage> {
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val item = array.getJSONObject(i)
            val answers = item.getJSONArray("acceptableAnswers")
            Passage(
                id = item.getString("id"),
                reference = item.getString("reference"),
                text = item.getString("text"),
                recallQuestion = RecallQuestion(
                    prompt = item.getString("recallPrompt"),
                    acceptableAnswers = (0 until answers.length()).map { answers.getString(it) }
                )
            )
        }
    }
}