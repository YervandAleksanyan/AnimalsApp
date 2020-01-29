package com.task.animalsapp.viewmodel.di

import com.task.animalsapp.viewmodel.animals.cats.CatBootstrapper
import org.koin.core.module.Module

fun viewModelModules(): List<Module> {
    return listOf(
        CatBootstrapper.module
    )
}