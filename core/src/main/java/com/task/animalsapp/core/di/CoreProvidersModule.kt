package com.task.animalsapp.core.di

import com.task.animalsapp.core.providers.IConfigurationProvider
import com.task.animalsapp.core.providers.implementation.ConfigurationProvider
import org.koin.dsl.module

val coreProvidersModule = module {
    factory<IConfigurationProvider> { ConfigurationProvider() }
}