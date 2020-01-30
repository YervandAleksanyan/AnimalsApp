package com.task.animalsapp.viewmodel.animals.dogs.implementation

import com.task.animalsapp.viewmodel.animals.dogs.IDogsViewModel
import com.task.animalsapp.viewmodel.base.IAsyncCommand
import com.task.animalsapp.viewmodel.base.ICommand
import com.task.animalsapp.viewmodel.base.ICommandFactory
import com.task.animalsapp.viewmodel.base.implementation.BaseCommandFactory

internal class DogsViewModelCommandFactory(
    viewModel: IDogsViewModel
) : BaseCommandFactory(viewModel), ICommandFactory {
    val loadCommand: IAsyncCommand
        get() = getCommand<LoadDogsCommand>()

    val selectDogCommand: ICommand
        get() = getCommand<SelectDogCommand>()
}