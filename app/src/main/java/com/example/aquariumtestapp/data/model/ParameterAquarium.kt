package com.example.aquariumtestapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ParameterAquarium(
    val aquariumId : Int,
    val CL2: Float,
    val GH: Float,
    val KH: Float,
    val NO2: Float,
    val NO3: Float,
    val PH: Float,
    val TA: Float
)
@Serializable
data class ParameterAquariumGet ( // pour la récupération des paramètres via l'API python
    val CL2: Float,
    val GH: Float,
    val KH: Float,
    val NO2: Float,
    val NO3: Float,
    val PH: Float,
    val TA: Float
)

@Serializable
data class ParameterAquariumGetBdd( // pour la récupération des paramètres via la BDD
    val aquariumId : Int,
    val timestamp: String,
    val CL2: Float,
    val GH: Float,
    val KH: Float,
    val NO2: Float,
    val NO3: Float,
    val PH: Float,
    val TA: Float
)