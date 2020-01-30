package com.task.animalsapp.view.utils

import android.content.SharedPreferences

fun SharedPreferences.get(key: String, defValue: Int): Int {
    return if (contains(key)) {
        getInt(key, defValue)
    } else {
        -1
    }
}

fun SharedPreferences.set(key: String, value: Int) {
    val editor = this.edit()
    if (value != -1) {
        editor.putInt(key, value)
    } else {
        editor.remove(key)
    }
    editor.apply()
}