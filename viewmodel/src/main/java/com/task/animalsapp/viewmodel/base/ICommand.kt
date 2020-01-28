package com.task.animalsapp.viewmodel.base

import androidx.lifecycle.LiveData

interface ICommand {

    val isExecutable: LiveData<Boolean>

    fun execute()
}