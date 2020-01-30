package com.task.animalsapp.viewmodel.di

import com.task.animalsapp.viewmodel.animals.cats.CatsBootstrapper
import com.task.animalsapp.viewmodel.animals.details.AnimalDetailsBootstrapper
import com.task.animalsapp.viewmodel.animals.dogs.DogsBootstrapper
import org.koin.core.module.Module

fun viewModelModules(): List<Module> {
    return listOf(
        CatsBootstrapper.module,
        DogsBootstrapper.module,
        AnimalDetailsBootstrapper.module
    )
}