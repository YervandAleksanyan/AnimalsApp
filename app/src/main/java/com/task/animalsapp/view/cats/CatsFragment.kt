package com.task.animalsapp.view.cats

import android.view.View
import com.task.animalsapp.view.base.BaseAnimalFragment
import com.task.animalsapp.view.controls.bindableRecylcerView.eventHandlers.ClickHandler
import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalItemViewModel
import com.task.animalsapp.viewmodel.animals.cats.ICatsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatsFragment : BaseAnimalFragment() {

    companion object {
        fun newInstance() = CatsFragment()
    }

    override val viewModel: ICatsViewModel by viewModel()

    override fun getAnimalClickHandler(): ClickHandler<AnimalItemViewModel> =
        object : ClickHandler<AnimalItemViewModel> {
            override fun onClick(animalViewModel: AnimalItemViewModel?, view: View) {
                viewModel.catToSelect = animalViewModel
                viewModel.selectCatCommand.execute()
            }
        }

}