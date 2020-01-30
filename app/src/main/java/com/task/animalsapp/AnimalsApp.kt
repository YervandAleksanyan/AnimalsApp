package com.task.animalsapp

import android.app.Application
import com.task.animalsapp.core.di.coreModules
import com.task.animalsapp.viewmodel.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AnimalsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AnimalsApp)
            modules(
                coreModules() +
                        viewModelModules()
            )
        }
    }

}