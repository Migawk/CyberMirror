package com.cool.something.network

import com.cool.something.weatherApiKey
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json

class Weather {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }

    }

    suspend fun getWeather(city: String): CommonWeatherResponse {
        val response: CommonWeatherResponse = client.get("https://api.weatherapi.com/v1/forecast.json?key=$weatherApiKey&q=$city&days=3").body()

        return response
    }
}