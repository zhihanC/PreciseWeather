package hu.ait.preciseweather.network

import hu.ait.preciseweather.data.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.openweathermap.org/data/2.5/weather?q=Budapest,hu&units=imperial&appid=3e2054912dc894afedead2ddd82b270a
// HOST: https://api.openweathermap.org
// PATH: /data/2.5/weather
// QUERY params:
// ?q=Budapest,hu
// units=imperial
// appid=3e2054912dc894afedead2ddd82b270a

interface WeatherAPI {
    @GET("/data/2.5/weather")
    suspend fun getWeatherData(@Query("q") cityName: String,
                               @Query("units") units: String,
                               @Query("appid") appId: String): WeatherData
}