package hr.kurtovic.weatherkurtovi

import hr.kurtovic.weatherkurtovi.models.BasicWeatherInfo
import hr.kurtovic.weatherkurtovi.models.MainWeatherInfo
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import hr.kurtovic.weatherkurtovi.models.WindInfo


fun createWeatherInfo(): WeatherInfo {

    val givenWeatherInfoId: Long = 123
    val givenMainWeatherInfo = MainWeatherInfo(
        temperature = 10.23,
        humidity = 56.04,
        pressure = 1033.23
    )
    val givenWeatherArray = arrayOf(
        BasicWeatherInfo(
            id = 1,
            main = "Cloudy",
            description = "Mostly clouds",
            icon = "01d"
        )
    )

    val givenWindInfo = WindInfo(speed = 3.5, degree = 55.5)

    val givenName = "London"

    return WeatherInfo(
        id = givenWeatherInfoId,
        main = givenMainWeatherInfo,
        weather = givenWeatherArray,
        wind = givenWindInfo,
        name = givenName
    )
}