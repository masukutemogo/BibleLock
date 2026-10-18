package com.pishongroup.biblelock.lock.accessibility

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForegroundAppHistory(private val maxSize: Int = 5) {
    private val _recent = MutableStateFlow<List<String>>(emptyList())
    val recent: StateFlow<List<String>> = _recent.asStateFlow()

    fun record(packageName: String) {
        _recent.update { current ->
            if (current.firstOrNull() == packageName) current
            else (listOf(packageName) + current).take(maxSize)
        }
    }
}

object ForegroundApp {
    val history = ForegroundAppHistory()
}