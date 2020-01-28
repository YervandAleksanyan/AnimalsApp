package com.task.animalsapp.viewmodel.base

import com.task.animalsapp.core.di.coreModules
import com.task.animalsapp.testscore.base.BaseSpekTest
import com.task.animalsapp.testscore.di.MockKoin
import com.task.animalsapp.testscore.extensions.setupKoin
import com.task.animalsapp.viewmodel.base.extensions.instantTaskExecutorRule
import com.task.animalsapp.viewmodel.di.viewModelModules
import com.task.animalsapp.viewmodel.di.viewModelsTestModule
import org.spekframework.spek2.dsl.Root

internal abstract class SpekTest(rootBody: Root.() -> Unit) : BaseSpekTest(rootBody, {
    instantTaskExecutorRule()
    setupKoin(
        coreModules() +
                viewModelModules() +
                MockKoin.module + viewModelsTestModule
    )
})