package com.task.animalsapp.viewmodel.utils

import androidx.lifecycle.ViewModel
import com.task.animalsapp.core.utils.Scoped
import com.task.animalsapp.viewmodel.base.ICommandFactory
import org.koin.core.parameter.parametersOf
import org.koin.ext.getFullName

internal inline fun <reified CF : ICommandFactory> ViewModel.getCommandFactory(): CF =
    if (this is Scoped) {
        scope.get {
            parametersOf(this)
        }
    } else {
        throw UnsupportedOperationException("${this::class.getFullName()} should implement ${Scoped::class.getFullName()}")
    }