package com.task.animalsapp.viewmodel.animals.cats.implementation

import com.task.animalsapp.core.managers.IPreferencesManager
import com.task.animalsapp.viewmodel.base.implementation.BaseCommand

class SelectCatCommand(
    private val viewModel: CatsViewModel,
    private val preferencesManager: IPreferencesManager
) : BaseCommand() {

    override fun executeCore() {
        viewModel.items.value?.find { i -> i.id == viewModel.selectedCat?.id }?.isSelected = false
        viewModel.items.value?.find { i -> i.id == viewModel.catToSelect?.id }?.isSelected = true
        viewModel.selectedCat = viewModel.catToSelect
        preferencesManager.selectedCatId = viewModel.selectedCat?.id?.toInt() ?: -1
    }
}