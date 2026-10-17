package com.pishongroup.biblelock.data.seed

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class PassageJsonParserTest {

    private val json = """
        [
          {
            "id": "psalm-23",
            "reference": "Psalm 23",
            "text": "Yahweh is my shepherd.",
            "recallPrompt": "What is the Lord?",
            "acceptableAnswers": ["shepherd"]
          }
        ]
    """.trimIndent()

    @Test
    fun `parses passages and their recall questions`() {
        val passages = PassageJsonParser.parse(json)
        assertEquals(1, passages.size)
        assertEquals("psalm-23", passages[0].id)
        assertEquals(listOf("shepherd"), passages[0].recallQuestion.acceptableAnswers)
    }

    @Test
    fun `empty array gives an empty list`() {
        assertTrue(PassageJsonParser.parse("[]").isEmpty())
    }

    @Test
    fun `bundled passages file is valid`() {
        val passages = PassageJsonParser.parse(File("src/main/assets/passages.json").readText())
        assertTrue(passages.isNotEmpty())
        assertEquals("duplicate ids", passages.size, passages.map { it.id }.toSet().size)
        passages.forEach { p ->
            assertTrue("${p.id} has no text", p.text.isNotBlank())
            assertTrue("${p.id} has no answers", p.recallQuestion.acceptableAnswers.isNotEmpty())
            assertTrue(
                "${p.id}: no acceptable answer appears in the passage",
                p.recallQuestion.acceptableAnswers.any { p.text.contains(it, ignoreCase = true) }
            )
        }
    }
}