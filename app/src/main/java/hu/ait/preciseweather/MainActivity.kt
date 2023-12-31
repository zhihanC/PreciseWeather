package hu.ait.preciseweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import hu.ait.preciseweather.screen.CitiesScreen
import hu.ait.preciseweather.screen.WeatherScreen
import hu.ait.preciseweather.ui.theme.PreciseWeatherTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreciseWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreciseWeatherNavHost()
                }
            }
        }
    }
}

@Composable
fun PreciseWeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "cities"
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        composable("cities") {
            CitiesScreen(
                onNavigateToSummary = {
                    cityName -> navController.navigate(
                        "weatherdata/$cityName"
                    )
                }
            )
        }
        composable("weatherdata/{cityName}",
            arguments = listOf(
                navArgument("cityName"){type = NavType.StringType}
            )
        ) {
            val cityName = it.arguments?.getString("cityName")
            if (cityName != null) {
                WeatherScreen(
                    cityName = cityName
                )
            }
        }
    }
}