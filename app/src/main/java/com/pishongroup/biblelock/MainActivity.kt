package com.pishongroup.biblelock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pishongroup.biblelock.domain.model.Passage
import com.pishongroup.biblelock.domain.usecase.SelectDailyPassageUseCase
import com.pishongroup.biblelock.ui.theme.BibleLockTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val app = application as BibleLockApp
        setContent {
            BibleLockTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodayScreen(app, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun TodayScreen(app: BibleLockApp, modifier: Modifier = Modifier) {
    val passage by produceState<Passage?>(initialValue = null) {
        withContext(Dispatchers.IO) { app.data.passageSeeder.seed() }
        val all = app.data.passageRepository.getAllPassages()
        value = SelectDailyPassageUseCase()(all, LocalDate.now())
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            val current = passage
            if (current == null) {
                CircularProgressIndicator()
            } else {
                Text(current.reference, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(16.dp))
                Text(current.text, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Text("Powered by Pishon Group", style = MaterialTheme.typography.labelMedium)
    }
}