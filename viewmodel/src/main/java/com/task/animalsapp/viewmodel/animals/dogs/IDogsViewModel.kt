package com.task.animalsapp.viewmodel.animals.dogs

import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.base.implementation.BaseAnimalViewModel
import com.task.animalsapp.viewmodel.base.ICommand

abstract class IDogsViewModel : BaseAnimalViewModel() {
    abstract var dogToSelect: AnimalItemViewModel?

    abstract var selectedDog: AnimalItemViewModel?

    abstract val selectDogCommand: ICommand

}