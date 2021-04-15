package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import kotlin.math.roundToInt

data class WeatherInfoUiModel(

    val name: String,
    val temperature: String,
    val humidity: String,
    val pressure: String,
    val windSpeed: String,
    val windDirection: String,
    val iconId: String,
    val basicDescription: String

)

fun fromWeatherInfoToUiModel(weatherInfo: WeatherInfo): WeatherInfoUiModel {

    val name = weatherInfo.name
    val temperature = weatherInfo.main.temperature.roundToInt().toString()
    val humidity = weatherInfo.main.humidity.roundToInt().toString()
    val pressure = weatherInfo.main.pressure.roundToInt().toString()
    val windSpeed = weatherInfo.wind.speed.roundToInt().toString()
    val iconId = weatherInfo.weather[0].icon
    val basicDescription = weatherInfo.weather[0].main

    val windDirection = when (weatherInfo.wind.degree.roundToInt()) {
        0 -> "N"
        in 0..89 -> "NE"
        90 -> "E"
        in 91..179 -> "SE"
        180 -> "S"
        in 181..269 -> "SW"
        270 -> "W"
        in 270..359 -> "NW"
        else -> "N"
    }

    return WeatherInfoUiModel(
        name = name,
        temperature = temperature,
        humidity = humidity,
        pressure = pressure,
        windSpeed = windSpeed,
        windDirection = windDirection,
        iconId = iconId,
        basicDescription = basicDescription
    )
}
