package com.task.animalsapp.viewmodel.animals.cats

import com.task.animalsapp.viewmodel.animals.cats.implementation.CatsViewModel
import com.task.animalsapp.viewmodel.animals.cats.implementation.CatsViewModelCommandFactory
import com.task.animalsapp.viewmodel.animals.cats.implementation.LoadCatsCommand
import com.task.animalsapp.viewmodel.animals.cats.implementation.SelectCatCommand
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CatsBootstrapper {
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
                scoped { (viewModel: CatsViewModel) ->
                    LoadCatsCommand(
                        viewModel,
                        get()
                    )
                }
                scoped { (viewModel: CatsViewModel) ->
                    SelectCatCommand(
                        viewModel
                    )
                }
            }
        }
}