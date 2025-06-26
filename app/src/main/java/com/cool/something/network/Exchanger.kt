package com.cool.something.network

import com.cool.something.exchangerApiKey
import com.cool.something.weatherApiKey
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

class Exchanger {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }

    }

    suspend fun getValue(currency: String = "USD"): ExchangerResponse {
        val response: ExchangerResponse = client.get("https://v6.exchangerate-api.com/v6/$exchangerApiKey/latest/$currency").body()

        return response

    }
}