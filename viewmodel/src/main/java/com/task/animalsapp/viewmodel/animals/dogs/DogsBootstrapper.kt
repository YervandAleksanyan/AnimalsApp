package com.task.animalsapp.viewmodel.animals.dogs

import com.task.animalsapp.viewmodel.animals.dogs.implementation.DogsViewModel
import com.task.animalsapp.viewmodel.animals.dogs.implementation.DogsViewModelCommandFactory
import com.task.animalsapp.viewmodel.animals.dogs.implementation.LoadDogsCommand
import com.task.animalsapp.viewmodel.animals.dogs.implementation.SelectDogCommand
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DogsBootstrapper {
    val module: Module
        get() = module {
            viewModel<IDogsViewModel> { DogsViewModel() }

            scope(named<DogsViewModel>()) {
                scoped { (viewModel: IDogsViewModel) ->
                    DogsViewModelCommandFactory(
                        viewModel
                    )
                }
            }

            scope(named<DogsViewModelCommandFactory>()) {
                scoped { (viewModel: DogsViewModel) ->
                    LoadDogsCommand(
                        viewModel,
                        get(),
                        get()
                    )
                }
                scoped { (viewModel: DogsViewModel) ->
                    SelectDogCommand(
                        viewModel,
                        get()
                    )
                }
            }
        }
}