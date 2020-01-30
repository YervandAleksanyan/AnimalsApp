package com.task.animalsapp.viewmodel.animals.details.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.animalsapp.core.utils.Scoped
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.details.IAnimalDetailsViewModel
import com.task.animalsapp.viewmodel.base.ICommand
import com.task.animalsapp.viewmodel.base.ImmutableLiveData
import com.task.animalsapp.viewmodel.utils.getCommandFactory

class AnimalDetailsViewModel : IAnimalDetailsViewModel(), Scoped {

    private val commandFactory = getCommandFactory<AnimalDetailsViewModelCommandFactory>()

    internal val animalItemMutable = MutableLiveData<AnimalItemViewModel>()

    override val animalItem: LiveData<AnimalItemViewModel> = ImmutableLiveData(animalItemMutable)

    override val setupCommand: ICommand = commandFactory.setupCommand


    override fun onCleared() {
        super.onCleared()
        closeScope()
        commandFactory.dispose()
    }
}