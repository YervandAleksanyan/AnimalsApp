package com.task.animalsapp.viewmodel.base.extensions

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.spekframework.spek2.style.gherkin.FeatureBody

/**
 * A JUnit Test Rule that swaps the background executor used by the Architecture Components with a
 * different one which executes each task synchronously.
 * <p>
 * You can use this rule for your host side tests that use Architecture Components.
 */
fun FeatureBody.instantTaskExecutorRule() {
    beforeGroup {
        val taskExecutor = object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

            override fun postToMainThread(runnable: Runnable) = runnable.run()

            override fun isMainThread() = true
        }
        ArchTaskExecutor.getInstance().setDelegate(taskExecutor)
    }
    afterGroup {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}
