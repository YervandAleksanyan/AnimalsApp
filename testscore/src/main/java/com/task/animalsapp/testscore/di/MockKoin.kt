package com.task.animalsapp.testscore.di

import android.content.Context
import com.providers.IConfigurationProvider
import com.task.animalsapp.testscore.extensions.port
import io.mockk.every
import io.mockk.mockk
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

object MockKoin {
    val module: Module
        get() = module {
            single(override = true) { mockContext() }
            single { mockConfigurationProvider() }
        }


    private fun mockContext() = mockk<Context>()

    private fun Scope.mockConfigurationProvider() = mockk<IConfigurationProvider>().apply {
        every { apiBaseUrl } returns "http://localhost:$port/api/"
    }

}