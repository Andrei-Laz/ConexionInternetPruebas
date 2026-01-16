import kotlinx.coroutines.*

/*In this case the exeption is being cought by the runBlocking method*/

fun main() {
    runBlocking {
        println("Weather forecast: ")
        try {
            println(getWeatherReport())
            println("Have a good day!")
        } catch (e: AssertionError) {
            println("Caught exception in runBlocking(): $e")
            println("Report unavailable at this time")
        }
    }
}

private suspend fun getWeatherReport() = coroutineScope {
    val forecast: Deferred<String> = async { getForecast() }
    val temperature: Deferred<String> = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

private suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

private suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}

