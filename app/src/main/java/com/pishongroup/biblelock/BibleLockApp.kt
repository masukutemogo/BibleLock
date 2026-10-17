package com.pishongroup.biblelock

import android.app.Application
import com.pishongroup.biblelock.data.BibleLockData

class BibleLockApp : Application() {
    val data: BibleLockData by lazy { BibleLockData(this) }
}