package com.task.animalsapp.di

import org.koin.core.module.Module

fun appModules(): List<Module> {
    return listOf(
        managersModule
    )
}