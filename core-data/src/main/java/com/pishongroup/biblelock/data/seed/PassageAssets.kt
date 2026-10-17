package com.pishongroup.biblelock.data.seed

import android.content.Context

fun Context.readPassagesAsset(): String =
    assets.open("passages.json").bufferedReader().use { it.readText() }