package com.task.animalsapp.testscore.extensions

import com.task.animalsapp.testscore.utils.Port
import org.koin.core.scope.Scope

val Scope.port: Port
    get() = getOrNull<Port>() ?: Port()