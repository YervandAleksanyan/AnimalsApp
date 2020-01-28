package com.task.animalsapp.viewmodel.base.features.base

import androidx.lifecycle.Observer
import com.task.animalsapp.viewmodel.base.SpekTest
import com.task.animalsapp.viewmodel.base.implementation.BaseCommand
import io.kotlintest.shouldBe
import org.spekframework.spek2.style.gherkin.Feature

internal class CommandFeature : SpekTest({
    Feature("BaseCommand") {

        Scenario("Command can execute") {
            var isCommandExecutable = false
            val observer = Observer<Boolean> { isCommandExecutable = it }
            val command = MockCommand()

            beforeGroup { command.isExecutable.observeForever(observer) }
            afterGroup { command.isExecutable.removeObserver(observer) }

            Given("the command is not executable") {
                command.setCanExecuteState(false)
            }
            When("the command is enabled for execution") {
                command.setCanExecuteState(true)
            }
            Then("the view should be notified that the execution state has been changed") {
                isCommandExecutable shouldBe true
            }
        }
    }
}) {
    private class MockCommand : BaseCommand() {

        override fun executeCore() {}

        fun setCanExecuteState(canExecute: Boolean) {
            setCanExecute(canExecute)
        }
    }
}

