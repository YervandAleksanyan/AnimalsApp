package com.task.animalsapp.view.dogs

import android.view.View
import com.task.animalsapp.view.animaldetails.AnimalDetailsActivity
import com.task.animalsapp.view.base.BaseAnimalFragment
import com.task.animalsapp.view.controls.bindableRecylcerView.eventHandlers.ClickHandler
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.dogs.IDogsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DogsFragment : BaseAnimalFragment() {

    companion object {
        fun newInstance() = DogsFragment()
    }

    override val viewModel: IDogsViewModel by viewModel()

    override fun getAnimalClickHandler(): ClickHandler<AnimalItemViewModel> =
        object : ClickHandler<AnimalItemViewModel> {
            override fun onClick(animalViewModel: AnimalItemViewModel?, view: View) {
                viewModel.dogToSelect = animalViewModel
                viewModel.selectDogCommand.execute()
                AnimalDetailsActivity.start(context!!, viewModel.selectedDog!!)
            }
        }
}