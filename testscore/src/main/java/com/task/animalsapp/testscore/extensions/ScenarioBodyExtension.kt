package com.task.animalsapp.testscore.extensions

import org.spekframework.spek2.dsl.TestBody
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.meta.Description
import org.spekframework.spek2.meta.DescriptionLocation
import org.spekframework.spek2.meta.Descriptions
import org.spekframework.spek2.style.gherkin.ScenarioBody

fun <K, V> ScenarioBody.examples(
    vararg pairs: Pair<K, V>,
    body: ScenarioBody.(inputValue: K, expectedResult: V) -> Unit
) {
    pairs.forEach { pair ->
        delegate.group(
            "${pair.first} -> ${pair.second}",
            defaultCachingMode = CachingMode.SCOPE,
            preserveExecutionOrder = true,
            failFast = true
        ) {
            body(
                ScenarioBody(this),
                pair.first,
                pair.second
            )
        }
    }
}

@Descriptions(Description(DescriptionLocation.VALUE_PARAMETER, 0))
fun ScenarioBody.But(
    description: String,
    timeout: Long = defaultTimeout,
    body: TestBody.() -> Unit
) {
    delegate.test("But: $description", timeout = timeout, body = body)
}
