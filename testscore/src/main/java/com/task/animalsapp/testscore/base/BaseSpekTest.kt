package com.task.animalsapp.testscore.base


import com.task.animalsapp.testscore.extensions.scenario
import com.task.animalsapp.testscore.extensions.setupTestCoroutineDispatcher
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.spyk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.gherkin.Feature
import org.spekframework.spek2.style.gherkin.FeatureBody

abstract class BaseSpekTest(
    private val rootBody: Root.() -> Unit,
    private val featureConfiguration: (FeatureBody.() -> Unit)? = null
) : Spek({
    mockkStatic("org.spekframework.spek2.style.gherkin.GherkinStyleKt") {
        every { Feature(any(), any()) } answers {
            val description: String = secondArg()
            val body: FeatureBody.() -> Unit = thirdArg()

            mockFeature(description) {
                mockScenario()
                setupTestCoroutineDispatcher()
                featureConfiguration?.let { apply(it) }
                apply(body)
            }
        }
        rootBody(this)
    }
})

private fun Root.mockFeature(description: String, body: FeatureBody.() -> Unit) {
    group("Feature: $description", defaultCachingMode = CachingMode.EACH_GROUP) {
        val feature = spyk(FeatureBody(this))
        body(feature)
    }
}


private fun FeatureBody.mockScenario() {
    every { Scenario(any(), any()) } answers {
        scenario(firstArg()) {
            beforeGroup {
                clearAllMockVerificationRecords()
            }
            apply(secondArg())
        }
    }
}

private fun clearAllMockVerificationRecords() {
    clearAllMocks(
        answers = false,
        recordedCalls = true,
        childMocks = false,
        regularMocks = false,
        objectMocks = false,
        staticMocks = false
    )
}