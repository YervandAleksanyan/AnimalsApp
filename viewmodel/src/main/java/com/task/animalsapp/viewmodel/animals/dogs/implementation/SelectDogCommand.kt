package com.task.animalsapp.viewmodel.animals.dogs.implementation

import com.task.animalsapp.viewmodel.base.implementation.BaseCommand

class SelectDogCommand(
    private val viewModel: DogsViewModel
) : BaseCommand() {

    override fun executeCore() {
        viewModel.items.value?.find { i -> i.id == viewModel.selectedDog?.id }?.isSelected = false
        viewModel.items.value?.find { i -> i.id == viewModel.dogToSelect?.id }?.isSelected = true
        viewModel.selectedDog = viewModel.dogToSelect
    }
}