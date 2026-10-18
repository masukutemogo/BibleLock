package com.pishongroup.biblelock.lock.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class ForegroundAppService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        val packageName = event.packageName?.toString() ?: return
        ForegroundApp.history.record(packageName)
    }

    override fun onInterrupt() = Unit
}