package com.task.animalsapp.core.di

import org.koin.core.module.Module

fun coreModules(): List<Module> {
    return listOf(
        coreProvidersModule,
        networkModule,
        coreServicesModule
    )
}