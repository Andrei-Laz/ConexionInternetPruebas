package org.example

import kotlin.system.*
import kotlinx.coroutines.*

fun main() {
    val time = measureTimeMillis{
        runBlocking {
            println("Weather forecast: ")
            getWeatherReport()
            println("Have a good day!")
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}

suspend fun getForecast(): String {
    delay(3000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}

suspend fun getWeatherReport() = coroutineScope {


    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    println("${forecast.await()} ${temperature.await()}")


}

