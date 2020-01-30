package com.task.animalsapp.viewmodel.animals.base.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.animalsapp.viewmodel.animals.base.IAnimalBaseViewModel
import com.task.animalsapp.viewmodel.base.ImmutableLiveData

abstract class BaseAnimalViewModel : ViewModel(),
    IAnimalBaseViewModel {

    internal val itemsMutable = MutableLiveData<List<AnimalItemViewModel>>()

    override val items: LiveData<List<AnimalItemViewModel>> = ImmutableLiveData(itemsMutable)

}