package hu.ait.preciseweather.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.preciseweather.data.WeatherData
import hu.ait.preciseweather.network.WeatherAPI
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val weatherAPI: WeatherAPI
) : ViewModel() {
    private var _citiesList =
        mutableStateListOf<String>()

    fun getAllCitiesList(): List<String> {
        return _citiesList;
    }
    fun addCity(city: String) {
        _citiesList.add(city)
    }
    fun removeCity(city: String) {
        _citiesList.remove(city)
    }

    var weatherUIState: WeatherUiState by mutableStateOf(WeatherUiState.Init)

    fun getWeatherData(cityName: String, units: String, appId: String) {
        // exec network call and change UI states properly..
        weatherUIState = WeatherUiState.Loading

        viewModelScope.launch {
            try {
                val weatherData = weatherAPI.getWeatherData(cityName, units, appId)

                weatherUIState = WeatherUiState.Success(weatherData)
            } catch (e: Exception) {
                weatherUIState = WeatherUiState.Error(e.message!!)
            }
        }
    }
}

sealed interface WeatherUiState {
    object Init : WeatherUiState
    object Loading : WeatherUiState
    data class Success(val weatherData: WeatherData) : WeatherUiState
    data class Error(val errorMsg: String) : WeatherUiState
}