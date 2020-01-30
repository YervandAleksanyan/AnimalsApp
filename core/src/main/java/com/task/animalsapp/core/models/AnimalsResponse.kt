package com.task.animalsapp.core.models

data class AnimalsResponse(
    val `data`: List<Data>,
    val message: String
)

data class Data(
    val title: String,
    val url: String
)