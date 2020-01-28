package com.task.animalsapp.viewmodel.base.implementation


abstract class OnceExecutableCommand : BaseCommand() {
    abstract fun executeOnce()

    final override fun executeCore() {
        executeOnce()
        setCanExecute(false)
    }
}