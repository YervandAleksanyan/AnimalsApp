package com.task.animalsapp.viewmodel.base.features.base


import com.task.animalsapp.viewmodel.base.SpekTest
import com.task.animalsapp.viewmodel.base.implementation.OnceExecutableCommand
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.spekframework.spek2.style.gherkin.Feature

internal class OnceExecutableCommandFeature : SpekTest({
    Feature("OnceExecutableCommand") {

        Scenario("Command must be executed only once") {
            val command = spyk<OnceExecutableCommand>(recordPrivateCalls = true).also {
                every { it["executeOnce"]() } answers {}
            }

            fun checkNumberOfExecution() {
                verify(exactly = 1) { command["executeOnce"]() }
            }

            Given("OnceExecutableCommand is mocked") {}
            When("the command is executed") {
                command.execute()
            }
            Then("number of executions should be 1") {
                checkNumberOfExecution()
            }
            When("the command is executed again") {
                command.execute()
            }
            Then("number of executions should not change and remain 1") {
                checkNumberOfExecution()
            }
        }
    }
})