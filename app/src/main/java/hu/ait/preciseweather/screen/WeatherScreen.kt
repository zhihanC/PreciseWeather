package hu.ait.preciseweather.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hu.ait.preciseweather.data.WeatherData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    citiesViewModel: CitiesViewModel = hiltViewModel(),
    cityName: String
) {
    Column() {
        LaunchedEffect(Unit) {
            citiesViewModel.getWeatherData(cityName, "imperial", "3e2054912dc894afedead2ddd82b270a")
        }

        TopAppBar(
            title = { Text(text = "Precise Weather: ${cityName}") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer)
        )

        //state machine
        when (citiesViewModel.weatherUIState) {
            is WeatherUiState.Init -> Text(text = "Press the button...")
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Success -> ResultView(
                weatherData = (citiesViewModel.weatherUIState as WeatherUiState.Success).weatherData
            )
            is WeatherUiState.Error ->
                Column(
                    modifier = modifier.padding(10.dp)
                ) {
                    Text(
                        text = "Error:" +
                                " ${(citiesViewModel.weatherUIState as WeatherUiState.Error).errorMsg}"
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Something went wrong! Please delete this city card and try again.")
                }
        }
    }
}

@Composable
fun ResultView(weatherData: WeatherData) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://openweathermap.org/img/w/${
                    weatherData.weather?.get(0)?.icon
                }.png")
                .crossfade(true)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).clip(CircleShape)
        )

        Text(text = "Temperature in ${weatherData.name}")
        Text(text ="Current Temperature: ${weatherData.main?.temp}°F")
        Text(text ="Highest: ${weatherData.main?.tempMax}°F")
        Text(text ="Lowest: ${weatherData.main?.tempMin}°F")
        Text(text ="Feels like: ${weatherData.main?.feelsLike}°F")
    }
}