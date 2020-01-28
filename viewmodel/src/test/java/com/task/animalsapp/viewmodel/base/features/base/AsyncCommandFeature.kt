package com.task.animalsapp.viewmodel.base.features.base

import com.task.animalsapp.viewmodel.R
import com.task.animalsapp.viewmodel.base.SpekTest
import com.task.animalsapp.viewmodel.base.extensions.defineMockResources
import com.task.animalsapp.viewmodel.base.implementation.BaseAsyncCommand
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.spekframework.spek2.style.gherkin.Feature
import java.net.UnknownHostException

@UseExperimental(ExperimentalCoroutinesApi::class)
internal class AsyncCommandFeature : SpekTest({
    Feature("BaseAsyncCommand") {
        val dispatcher by memoized<TestCoroutineDispatcher>()
        val command by memoized {
            spyk<BaseAsyncCommand>(recordPrivateCalls = true).also {
                coEvery { it["executeCoreAsync"]() } coAnswers {
                    delay(Long.MAX_VALUE)
                    true
                }
            }
        }

        fun createCommandWithNetworkIssues() =
            spyk<BaseAsyncCommand>(recordPrivateCalls = true).also {
                coEvery { it["executeCoreAsync"]() } coAnswers {
                    throw UnknownHostException()
                }
            }

        Scenario("'isBusy' should be set to true while the operation is in progress") {
            When("the command started execution") {
                command.execute()
            }

            Then("'isBusy' should be set to true") {
                command.isBusy.value shouldBe true
            }
            When("the command has finished execution") {
                dispatcher.advanceUntilIdle()
            }
            Then("'isBusy'should be set to false") {
                command.isBusy.value shouldBe false
            }
        }

        Scenario("Command execution completes successfully") {
            When("the command starts execution") {
                command.execute()
            }
            Then("'isSuccessful' should be set to false initially") {
                command.isSuccessful.value shouldBe false
            }
            And("'exception' should be set to null initially") {
                command.exception shouldBe null
            }
            And("'failureMessage' should be empty initially") {
                command.failureMessage.value shouldBe null
            }
            When("the command is finished execution successfully") {
                dispatcher.advanceUntilIdle()
            }
            Then("'isSuccessful' should be set to true") {
                command.isSuccessful.value shouldBe true
            }
            And("'failureMessage' should be empty") {
                command.failureMessage.value shouldBe null
            }
        }

        Scenario("Command execution fails") {
            val unknownError = "UNKNOWN_ERROR"
            defineMockResources(
                R.string.unknown_error to unknownError
            )

            When("the command is started execution") {
                command.execute()
            }
            Then("'isSuccessful' should be set to false initially") {
                command.isSuccessful.value shouldBe false
            }
            And("'exception'should be set to null initially") {
                command.exception shouldBe null
            }
            And("'failureMessage' should be empty initially") {
                command.failureMessage.value shouldBe null
            }
            When("command execution fails", Long.MAX_VALUE) {
                command.cancelJobs()
            }
            Then("'isSuccessful' should be set to false") {
                command.isSuccessful.value shouldBe false
            }
            And("'failureMessage' should be set to generic failure message") {
                command.failureMessage.value shouldBe unknownError
            }
        }

        Scenario("Command may have multiple parallel execution") {
            When("the command is executed 5 times") {
                repeat(5) {
                    command.execute()
                }
            }
            Then("command should be executable") {
                command.isExecutable.value shouldBe true
            }
            When("command is finished execution successfully") {
                dispatcher.advanceUntilIdle()
            }
            Then("method 'executeCoreAsync' should be called 5 times") {
                verify(exactly = 5) {
                    command["executeCoreAsync"]()
                }
            }
        }
    }
})
