package com.task.animalsapp.viewmodel.animals.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.base.ICommand

abstract class IAnimalDetailsViewModel : ViewModel() {
    abstract val animalItem: LiveData<AnimalItemViewModel>
    abstract val setupCommand: ICommand
}