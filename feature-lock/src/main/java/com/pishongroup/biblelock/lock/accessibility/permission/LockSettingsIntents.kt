package com.pishongroup.biblelock.lock.permission

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object LockSettingsIntents {
    fun overlay(context: Context) = Intent(
        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
        Uri.parse("package:${context.packageName}")
    )

    fun accessibility() = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)

    fun doNotDisturb() = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
}