package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import hr.kurtovic.weatherkurtovi.api.WeatherApi
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import io.reactivex.Single
import javax.inject.Inject

interface WeatherInfoService {

    fun getWeatherInfoFor(cityName: String): Single<WeatherInfo>

}

class WeatherInfoServiceImpl @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherInfoService {

    override fun getWeatherInfoFor(cityName: String): Single<WeatherInfo> =
            weatherApi.fetchCityWeatherInfo(cityName)


}
