package com.pishongroup.biblelock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pishongroup.biblelock.lock.accessibility.ForegroundApp
import com.pishongroup.biblelock.lock.permission.LockPermissionChecker
import com.pishongroup.biblelock.lock.permission.LockSettingsIntents

@Composable
fun PermissionsPanel(
    checker: LockPermissionChecker,
    refreshTick: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val overlay = remember(refreshTick) { checker.hasOverlay() }
    val accessibility = remember(refreshTick) { checker.hasAccessibility() }
    val dnd = remember(refreshTick) { checker.hasDoNotDisturb() }
    val recent by ForegroundApp.history.recent.collectAsState()

    Column(modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
        PermissionRow("Display over other apps", overlay) {
            context.startActivity(LockSettingsIntents.overlay(context))
        }
        PermissionRow("Accessibility service", accessibility) {
            context.startActivity(LockSettingsIntents.accessibility())
        }
        PermissionRow("Do Not Disturb access", dnd) {
            context.startActivity(LockSettingsIntents.doNotDisturb())
        }
        Text(
            "Recent foreground apps: " +
                    if (recent.isEmpty()) "none yet" else recent.joinToString(" ← "),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun PermissionRow(label: String, granted: Boolean, onGrant: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "$label: ${if (granted) "granted" else "not granted"}",
            style = MaterialTheme.typography.bodyMedium
        )
        if (!granted) TextButton(onClick = onGrant) { Text("Grant") }
    }
}