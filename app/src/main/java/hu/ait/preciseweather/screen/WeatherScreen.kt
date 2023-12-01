package hu.ait.preciseweather.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    cityName: String

) {
    Text(text= "On Weather Screen: I don't know the weather in ${cityName} yet!")
}