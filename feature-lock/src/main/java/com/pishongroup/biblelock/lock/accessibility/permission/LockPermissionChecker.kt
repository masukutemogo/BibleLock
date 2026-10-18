package com.pishongroup.biblelock.lock.permission

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import com.pishongroup.biblelock.lock.accessibility.ForegroundAppService

class LockPermissionChecker(private val context: Context) {

    fun hasOverlay(): Boolean = Settings.canDrawOverlays(context)

    fun hasDoNotDisturb(): Boolean =
        context.getSystemService(NotificationManager::class.java).isNotificationPolicyAccessGranted

    fun hasAccessibility(): Boolean {
        val ours = ComponentName(context, ForegroundAppService::class.java)
        val enabled = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false
        return enabled.split(':').any { ComponentName.unflattenFromString(it) == ours }
    }
}