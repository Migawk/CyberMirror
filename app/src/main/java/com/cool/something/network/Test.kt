package com.cool.something.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Test {
    private val client = HttpClient(OkHttp)

    suspend fun getResponse(): String {
        val response = client.get("https://migawka.site")
        return response.bodyAsText()
    }
}