package hu.ait.preciseweather.network

import hu.ait.preciseweather.data.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.openweathermap.org/data/2.5/weather?q=Budapest,hu&units=imperial&appid=642db3e6b246b9d67f9e1a92ec6645c7
// HOST: https://api.openweathermap.org
// PATH: /data/2.5/weather
// QUERY params:
// ?q=Budapest,hu
// units=imperial
// appid=642db3e6b246b9d67f9e1a92ec6645c7

interface WeatherAPI {

    @GET("api/latest")
    suspend fun getForecast(@Query("access_key") accessKey: String): WeatherData

}