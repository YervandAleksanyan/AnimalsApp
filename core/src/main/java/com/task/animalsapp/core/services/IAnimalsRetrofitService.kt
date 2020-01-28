package com.task.animalsapp.core.services

import com.task.animalsapp.core.models.AnimalsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface IAnimalsRetrofitService {
    @GET("api.php/")
    fun getAnimalsAsync(@Query("query") animalName: String): Deferred<AnimalsResponse>
}