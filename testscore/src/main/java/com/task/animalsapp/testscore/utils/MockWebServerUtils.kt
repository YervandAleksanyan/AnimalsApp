package com.task.animalsapp.testscore.utils

import okhttp3.mockwebserver.MockWebServer
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

data class Port(val value: Int = 1111) {
    override fun toString(): String = value.toString()
}

fun startServer(koin: KoinComponent): MockWebServer {
    return MockWebServer().also { server ->
        server.start()

        koin.apply {
            loadKoinModules(
                module {
                    single { Port(server.port) }
                }
            )
        }
    }
}

fun MockWebServer.initilize() {
}
