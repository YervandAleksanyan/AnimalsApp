package com.task.animalsapp.viewmodel.animals.details

import com.task.animalsapp.viewmodel.animals.details.implementation.AnimalDetailsViewModel
import com.task.animalsapp.viewmodel.animals.details.implementation.AnimalDetailsViewModelCommandFactory
import com.task.animalsapp.viewmodel.animals.details.implementation.SetupAnimalDetailsCommand
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AnimalDetailsBootstrapper {

    val module: Module
        get() = module {
            viewModel<IAnimalDetailsViewModel> { AnimalDetailsViewModel() }

            scope(named<AnimalDetailsViewModel>()) {
                scoped { (viewModel: IAnimalDetailsViewModel) ->
                    AnimalDetailsViewModelCommandFactory(
                        viewModel
                    )
                }
            }

            scope(named<AnimalDetailsViewModelCommandFactory>()) {
                scoped { (viewModel: AnimalDetailsViewModel) ->
                    SetupAnimalDetailsCommand(
                        viewModel,
                        get()
                    )
                }
            }
        }
}