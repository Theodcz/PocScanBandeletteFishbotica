package com.example.aquariumtestapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AquariumInsert (
    val uuid: String,
    val name: String,
    val volume: Int,
)

@Serializable
data class AquariumSelect (
    val aquariumId : Int,
    val uuid: String,
    val volume: Int,
    val name: String,
)