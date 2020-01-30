package com.task.animalsapp.viewmodel.animals.cats.implementation

import com.task.animalsapp.core.managers.IPreferencesManager
import com.task.animalsapp.core.services.IAnimalsApiService
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.base.implementation.BaseAsyncCommand

class LoadCatsCommand(
    private val viewModel: CatsViewModel,
    private val apiService: IAnimalsApiService,
    private val preferencesManager: IPreferencesManager
) : BaseAsyncCommand() {

    override suspend fun executeCoreAsync(): Boolean {
        val response = apiService.getAnimalsAsync("cat").await().data
        var index = 0
        val result = response.map {
            index = index.inc()
            AnimalItemViewModel(
                id = index.toString(),
                title = it.title,
                url = it.url.replace("http", "https"),
                selected = index == preferencesManager.selectedCatId
            )
        }
        viewModel.selectedCat = result.find { it.id == preferencesManager.selectedCatId.toString() }
        viewModel.itemsMutable.value = result
        return true
    }
}