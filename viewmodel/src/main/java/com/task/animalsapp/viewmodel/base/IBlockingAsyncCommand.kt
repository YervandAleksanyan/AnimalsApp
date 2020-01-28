package com.task.animalsapp.viewmodel.base

interface IBlockingAsyncCommand : IAsyncCommand {
    var isBlocked: Boolean
}