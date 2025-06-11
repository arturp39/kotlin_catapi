package com.example.network.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatImageModel(
    val id: String,
    val url: String,
    val width: Int? = null,
    val height: Int? = null,
    val breeds: List<Breed>? = null
)

@Serializable
data class Breed(
    val id: String,
    val name: String,
    val temperament: String? = null,
    val origin: String? = null,
    val life_span: String? = null,
    val wikipedia_url: String? = null,
    val country_code: String? = null,
    val description: String? = null,
    val weight: Weight? = null
)

@Serializable
data class Weight(
    val imperial: String? = null,
    val metric: String? = null
)

