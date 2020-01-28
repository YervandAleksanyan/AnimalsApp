package com.task.animalsapp.viewmodel.base.extensions

import com.task.animalsapp.viewmodel.utils.getString
import io.mockk.every
import io.mockk.mockkStatic
import org.spekframework.spek2.style.gherkin.ScenarioBody

fun ScenarioBody.defineMockResources(vararg resources: Pair<Int, String>) {
    this.beforeGroup {
        mockkStatic("com.task.animalsapp.viewmodel.utils.ResKt")
        resources.forEach {
            every { getString(it.first, *anyVararg()) } returns it.second
        }
    }
}
