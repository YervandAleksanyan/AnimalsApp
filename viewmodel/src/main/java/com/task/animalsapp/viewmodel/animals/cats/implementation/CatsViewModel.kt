package com.task.animalsapp.viewmodel.animals.cats.implementation

import com.task.animalsapp.core.utils.Scoped
import com.task.animalsapp.viewmodel.animals.cats.ICatsViewModel
import com.task.animalsapp.viewmodel.base.IAsyncCommand
import com.task.animalsapp.viewmodel.utils.getCommandFactory

class CatsViewModel : ICatsViewModel(), Scoped {

    private val commandFactory = getCommandFactory<CatsViewModelCommandFactory>()

    override val loadCommand: IAsyncCommand = commandFactory.loadCommand

    override fun onCleared() {
        super.onCleared()
        closeScope()
        commandFactory.dispose()
    }
}