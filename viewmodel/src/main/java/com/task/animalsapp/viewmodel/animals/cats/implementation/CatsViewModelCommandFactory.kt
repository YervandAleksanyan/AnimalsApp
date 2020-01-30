package com.task.animalsapp.viewmodel.animals.cats.implementation

import com.task.animalsapp.viewmodel.animals.cats.ICatsViewModel
import com.task.animalsapp.viewmodel.base.IAsyncCommand
import com.task.animalsapp.viewmodel.base.ICommand
import com.task.animalsapp.viewmodel.base.ICommandFactory
import com.task.animalsapp.viewmodel.base.implementation.BaseCommandFactory

internal class CatsViewModelCommandFactory(
    viewModel: ICatsViewModel
) : BaseCommandFactory(viewModel), ICommandFactory {
    val loadCommand: IAsyncCommand
        get() = getCommand<LoadCatsCommand>()

    val selectCatCommand: ICommand
        get() = getCommand<SelectCatCommand>()
}