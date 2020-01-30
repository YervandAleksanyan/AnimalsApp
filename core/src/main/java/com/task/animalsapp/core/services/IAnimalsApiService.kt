package com.task.animalsapp.core.services

import com.task.animalsapp.core.models.AnimalsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Path

interface IAnimalsApiService {
    suspend fun getAnimalsAsync(@Path("query") animalName: String): Deferred<AnimalsResponse>

}