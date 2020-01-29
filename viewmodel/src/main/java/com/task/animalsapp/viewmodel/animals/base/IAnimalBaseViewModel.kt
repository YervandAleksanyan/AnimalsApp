package com.task.animalsapp.viewmodel.animals.base

import androidx.lifecycle.LiveData
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.base.IAsyncCommand

interface IAnimalBaseViewModel {

    val items: LiveData<List<AnimalItemViewModel>>

    val loadCommand: IAsyncCommand

}