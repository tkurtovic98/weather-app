package hr.kurtovic.weatherkurtovi.api

import hr.kurtovic.weatherkurtovi.BuildConfig
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun fetchCityWeatherInfo(
        @Query("q") cityName: String,
        @Query("appid") appId: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "Metric"
    ): Single<WeatherInfo>
}
