package com.task.animalsapp.viewmodel.animals.cats

import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.base.implementation.BaseAnimalViewModel
import com.task.animalsapp.viewmodel.base.ICommand

abstract class ICatsViewModel : BaseAnimalViewModel() {
    abstract var catToSelect: AnimalItemViewModel?

    abstract var selectedCat: AnimalItemViewModel?

    abstract val selectCatCommand: ICommand

}