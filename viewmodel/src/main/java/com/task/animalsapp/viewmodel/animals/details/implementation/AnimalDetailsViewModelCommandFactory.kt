package com.task.animalsapp.viewmodel.animals.details.implementation

import com.task.animalsapp.viewmodel.animals.details.IAnimalDetailsViewModel
import com.task.animalsapp.viewmodel.base.ICommandFactory
import com.task.animalsapp.viewmodel.base.IDisposableCommand
import com.task.animalsapp.viewmodel.base.implementation.BaseCommandFactory

internal class AnimalDetailsViewModelCommandFactory(
    viewModel: IAnimalDetailsViewModel
) : BaseCommandFactory(viewModel), ICommandFactory {
    val setupCommand: IDisposableCommand
        get() = getCommand<SetupAnimalDetailsCommand>()


}