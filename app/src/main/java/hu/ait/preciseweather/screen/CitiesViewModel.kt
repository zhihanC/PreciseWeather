package hu.ait.preciseweather.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.preciseweather.network.WeatherAPI
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val weatherAPI: WeatherAPI
) : ViewModel() {

}