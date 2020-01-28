package com.task.animalsapp.testscore.extensions

import android.util.Log

fun String.x(action: () -> Unit) {
    println(this)
    Log.i("Test", this)
    action()
}
