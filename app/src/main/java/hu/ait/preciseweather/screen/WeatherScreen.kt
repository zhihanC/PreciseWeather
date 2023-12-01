package hu.ait.preciseweather.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.preciseweather.data.WeatherData

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    cityName: String
) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(text= "On Weather Screen: I don't know the weather in ${cityName} yet!")

        Button(onClick = {
            citiesViewModel.getWeatherData(
                cityName, "imperial", "642db3e6b246b9d67f9e1a92ec6645c7"
            )
        }) {
            Text(text = "Get weather data")
        }

//        citiesViewModel.getWeatherData(
//            cityName, "imperial", "642db3e6b246b9d67f9e1a92ec6645c7"
//        )

        //state machine
        when (citiesViewModel.weatherUIState) {
            is WeatherUiState.Init -> Text(text = "Press the button...")
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Success -> ResultView(
                weatherData = (citiesViewModel.weatherUIState as WeatherUiState.Success).weatherData)
            is WeatherUiState.Error ->
                Text(text = "Error:" +
                        " ${(citiesViewModel.weatherUIState as WeatherUiState.Error).errorMsg}")
        }
    }
}

@Composable
fun ResultView(weatherData: WeatherData) {
    Column() {
        Text(text = "Temperature in ${weatherData.name}")
        Text(text ="Temperature: ${weatherData.main?.temp}")
        Text(text ="Highest: ${weatherData.main?.tempMax}")
        Text(text ="Lowest: ${weatherData.main?.tempMin}")
    }
}