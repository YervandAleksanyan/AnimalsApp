package com.task.animalsapp.viewmodel.animals.dogs.implementation

import com.task.animalsapp.core.services.IAnimalsApiService
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.base.implementation.BaseAsyncCommand

class LoadDogsCommand(
    private val viewModel: DogsViewModel,
    private val apiService: IAnimalsApiService
) : BaseAsyncCommand() {

    override suspend fun executeCoreAsync(): Boolean {
        val response = apiService.getAnimalsAsync("dog").await().data
        var index = 0
        val result = response.map {
            index = index.inc()
            AnimalItemViewModel(
                id = index.toString(),
                title = it.title,
                url = it.url
            )
        }
        viewModel.itemsMutable.value = result
        return true
    }
}