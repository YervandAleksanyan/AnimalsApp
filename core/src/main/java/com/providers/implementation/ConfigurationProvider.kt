package com.providers.implementation

import com.providers.IConfigurationProvider
import com.task.animalsapp.core.BuildConfig

class ConfigurationProvider : IConfigurationProvider {

    override val apiBaseUrl: String = BuildConfig.ENDPOINT

}