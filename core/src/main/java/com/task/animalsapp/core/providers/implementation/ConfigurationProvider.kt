package com.task.animalsapp.core.providers.implementation

import com.task.animalsapp.core.BuildConfig
import com.task.animalsapp.core.providers.IConfigurationProvider

class ConfigurationProvider : IConfigurationProvider {

    override val apiBaseUrl: String = BuildConfig.ENDPOINT

}