package com.task.animalsapp.testscore.utils

import java.io.File

private const val RESPONSES_BASE_PATH = "testscore/src/main/assets/responses/"

private val responsesPath: String by lazy {
    if (File(RESPONSES_BASE_PATH).exists()) {
        RESPONSES_BASE_PATH
    } else {
        // If we run tests using "./gradlew" then the asset path must have the prefix "../"
        "../$RESPONSES_BASE_PATH"
    }
}

fun readResponseFromFile(filename: String): String = StringBuilder()
    .apply {
        File(responsesPath + filename)
            .bufferedReader()
            .useLines { linesSequence ->
                for (line in linesSequence) {
                    append(line)
                }
            }
    }.toString()