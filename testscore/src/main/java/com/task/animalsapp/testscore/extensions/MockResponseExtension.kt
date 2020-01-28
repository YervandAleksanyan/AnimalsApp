package com.task.animalsapp.testscore.extensions

import com.task.animalsapp.testscore.utils.readResponseFromFile
import okhttp3.mockwebserver.MockResponse
import java.io.IOException

@Throws(IOException::class)
fun MockResponse.setBodyFromAsset(filename: String): MockResponse {
    val body = readResponseFromFile(filename)
    setBody(body)
    return this
}