package com.github.juwit.keylight.service

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private val uri = URI("http://192.168.1.28:9123/elgato/lights")

fun on(): Int {
    val onBody = """
            {
              "lights": [
                {
                  "on": 1
                }
              ]
            }
        """.trimIndent()
    return sendRequest(onBody)
}

fun off(): Int {
    val onBody = """
            {
              "lights": [
                {
                  "on": 0
                }
              ]
            }
        """.trimIndent()
    return sendRequest(onBody)
}

fun changeBrightness(brightness: Int): Int {
    val body = """
            {
              "lights": [
                {
                  "brightness": $brightness
                }
              ]
            }
        """.trimIndent()
    return sendRequest(body)
}

fun changeColor(color: Int): Int {
    // régression linéaire basique
    val keyLightColor = -0.049 * color + 486
    val rounded = Math.round(keyLightColor).toInt()
    val body = """
            {
              "lights": [
                {
                  "temperature": $rounded
                }
              ]
            }
        """.trimIndent()
    return sendRequest(body)
}

fun sendRequest(body: String): Int {
    val getRequest = HttpRequest.newBuilder()
        .PUT(HttpRequest.BodyPublishers.ofString(body))
        .uri(uri)
        .build()

    val client = HttpClient.newHttpClient();

    val response: HttpResponse<String>
    try {
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() == 200) {
            return 0
        }
        return response.statusCode()
    } catch (e: Exception) {
        return 1
    }
}