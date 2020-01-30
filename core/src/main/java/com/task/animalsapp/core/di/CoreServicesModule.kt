package com.task.animalsapp.core.di

import com.task.animalsapp.core.services.IAnimalsApiService
import com.task.animalsapp.core.services.implementation.AnimalsApiService
import org.greenrobot.eventbus.EventBus
import org.koin.dsl.module

val coreServicesModule = module {
    factory<IAnimalsApiService> { AnimalsApiService(get()) }
    single { EventBus() }
}