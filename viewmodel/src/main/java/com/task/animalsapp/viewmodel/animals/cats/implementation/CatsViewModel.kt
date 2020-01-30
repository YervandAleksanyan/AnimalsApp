package com.task.animalsapp.viewmodel.animals.cats.implementation

import com.task.animalsapp.core.utils.Scoped
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.cats.ICatsViewModel
import com.task.animalsapp.viewmodel.base.IAsyncCommand
import com.task.animalsapp.viewmodel.base.ICommand
import com.task.animalsapp.viewmodel.utils.getCommandFactory

class CatsViewModel : ICatsViewModel(), Scoped {

    private val commandFactory = getCommandFactory<CatsViewModelCommandFactory>()

    override var catToSelect: AnimalItemViewModel? = null

    override var selectedCat: AnimalItemViewModel? = null

    override val loadCommand: IAsyncCommand = commandFactory.loadCommand

    override val selectCatCommand: ICommand = commandFactory.selectCatCommand

    override fun onCleared() {
        super.onCleared()
        closeScope()
        commandFactory.dispose()
    }
}