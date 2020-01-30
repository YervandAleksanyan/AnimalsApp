package com.task.animalsapp.di

import com.task.animalsapp.core.managers.IPreferencesManager
import com.task.animalsapp.managers.PreferencesManager
import org.koin.dsl.module

val managersModule = module {
    factory<IPreferencesManager> { PreferencesManager(get()) }
}