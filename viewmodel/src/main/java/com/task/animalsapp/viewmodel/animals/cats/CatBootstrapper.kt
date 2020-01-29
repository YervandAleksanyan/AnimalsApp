package com.task.animalsapp.viewmodel.animals.cats

import com.task.animalsapp.viewmodel.animals.base.implementation.AnimalBaseViewModel
import com.task.animalsapp.viewmodel.animals.cats.implementation.CatsViewModel
import com.task.animalsapp.viewmodel.animals.cats.implementation.CatsViewModelCommandFactory
import com.task.animalsapp.viewmodel.animals.cats.implementation.LoadCatsCommand
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CatBootstrapper {
    val module: Module
        get() = module {
            viewModel<ICatsViewModel> { CatsViewModel() }

            scope(named<CatsViewModel>()) {
                scoped { (viewModel: ICatsViewModel) ->
                    CatsViewModelCommandFactory(
                        viewModel
                    )
                }
            }

            scope(named<CatsViewModelCommandFactory>()) {
                scoped { (viewModel: AnimalBaseViewModel) ->
                    LoadCatsCommand(
                        viewModel,
                        get()
                    )
                }
            }
        }
}