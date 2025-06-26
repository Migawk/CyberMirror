package com.cool.something.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class ExchangerResponse(
    val base_code: String,
    val conversion_rates: Rates
)

@Serializable
@JsonIgnoreUnknownKeys
data class Rates (
    val USD: Float,
    val UAH: Float,
    val SEK: Float,
    val EUR: Float
)