package com.task.animalsapp.viewmodel.animals.dogs.implementation

import com.task.animalsapp.core.utils.Scoped
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.dogs.IDogsViewModel
import com.task.animalsapp.viewmodel.base.IAsyncCommand
import com.task.animalsapp.viewmodel.base.ICommand
import com.task.animalsapp.viewmodel.utils.getCommandFactory

class DogsViewModel : IDogsViewModel(), Scoped {

    private val commandFactory = getCommandFactory<DogsViewModelCommandFactory>()

    override var dogToSelect: AnimalItemViewModel? = null

    override var selectedDog: AnimalItemViewModel? = null

    override val loadCommand: IAsyncCommand = commandFactory.loadCommand

    override val selectDogCommand: ICommand = commandFactory.selectDogCommand

    override fun onCleared() {
        super.onCleared()
        closeScope()
        commandFactory.dispose()
    }
}