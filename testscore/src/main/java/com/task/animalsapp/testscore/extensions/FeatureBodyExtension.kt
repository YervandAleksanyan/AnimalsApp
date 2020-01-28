package com.task.animalsapp.testscore.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.meta.*
import org.spekframework.spek2.style.gherkin.FeatureBody
import org.spekframework.spek2.style.gherkin.ScenarioBody

/**
 * Override coroutine main dispatcher to [TestCoroutineDispatcher]
 */
@UseExperimental(ExperimentalCoroutinesApi::class)
fun FeatureBody.setupTestCoroutineDispatcher() {
    val dispatcher: TestCoroutineDispatcher by memoized(CachingMode.SCOPE) {
        TestCoroutineDispatcher()
    }

    beforeGroup {
        Dispatchers.setMain(dispatcher)
    }
    afterGroup {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
}

fun FeatureBody.setupKoin(modules: List<Module>) {

    class KoinTestImpl : KoinComponent {
        fun start(modules: List<Module>) {
            if (GlobalContext.getOrNull() == null) {
                startKoin {
                    modules(modules)
                }
            }
        }

        fun stop() {
            stopKoin()
        }
    }

    val koin: KoinComponent by memoized(
        factory = {
            KoinTestImpl().also {
                it.start(modules)
            }
        },
        destructor = { it.stop() }
    )
}

@Synonym(SynonymType.GROUP, prefix = "Scenario: ")
@Descriptions(Description(DescriptionLocation.VALUE_PARAMETER, 0))
internal fun FeatureBody.scenario(
    description: String,
    body: ScenarioBody.() -> Unit
): ScenarioBody {
    lateinit var scenarioBody: ScenarioBody

    delegate.group(
        "Scenario: $description",
        defaultCachingMode = CachingMode.SCOPE,
        preserveExecutionOrder = true,
        failFast = true
    ) {
        scenarioBody = ScenarioBody(this).apply(body)
    }
    return scenarioBody
}