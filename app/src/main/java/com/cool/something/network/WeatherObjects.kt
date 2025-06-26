package com.cool.something.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class CommonWeatherResponse (
    val current: Current,
    val forecast: Forecast
)

@Serializable
@JsonIgnoreUnknownKeys
data class Current (
    val temp_c: Float,
    val wind_kph: Float,
    val precip_mm: Float,
    val feelslike_c: Float,

    val condition: Condition
)

@Serializable
@JsonIgnoreUnknownKeys
data class Condition (
    val text: String
)

@Serializable
@JsonIgnoreUnknownKeys
data class Forecast (
    val forecastday: List<ForecastDay>
)

@Serializable
@JsonIgnoreUnknownKeys
data class ForecastDay (
    val date: String,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>
)

@Serializable
@JsonIgnoreUnknownKeys
data class Day (
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val totalprecip_mm: Float,
    val avgtemp_c: Float,

    val condition: Condition
)

@Serializable
@JsonIgnoreUnknownKeys
data class Astro (
    val sunrise: String,
    val sunset: String
)

@Serializable
@JsonIgnoreUnknownKeys
data class Hour (
    val temp_c: Float,
    val condition: Condition,
    val wind_kph: Float,
    val precip_mm: Float,
    val feelslike_c: Float
)