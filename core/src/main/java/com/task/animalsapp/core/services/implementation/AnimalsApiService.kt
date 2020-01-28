package com.task.animalsapp.core.services.implementation

import com.task.animalsapp.core.models.AnimalsResponse
import com.task.animalsapp.core.services.IAnimalsApiService
import com.task.animalsapp.core.services.IAnimalsRetrofitService
import kotlinx.coroutines.Deferred

class AnimalsApiService(private val retrofitService: IAnimalsRetrofitService) : IAnimalsApiService {

    override suspend fun getAnimalsAsync(animalName: String): Deferred<AnimalsResponse> =
        retrofitService.getAnimalsAsync(animalName)
}