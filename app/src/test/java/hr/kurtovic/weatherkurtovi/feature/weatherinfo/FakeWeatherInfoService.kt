package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import hr.kurtovic.weatherkurtovi.createWeatherInfo
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import io.reactivex.Single

class FakeWeatherInfoService : WeatherInfoService {

    private val availableCityNames = arrayListOf<String>().apply {
        add("London")
    }

    private val weatherInfo: WeatherInfo = createWeatherInfo()

    override fun getWeatherInfoFor(cityName: String): Single<WeatherInfo> {
        if (cityName in availableCityNames) {
            return Single.just(weatherInfo)
        }

        return Single.error(Throwable())
    }

}