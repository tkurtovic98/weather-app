package hr.kurtovic.weatherkurtovi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import hr.kurtovic.weatherkurtovi.feature.weatherinfo.WeatherInfoService
import hr.kurtovic.weatherkurtovi.feature.weatherinfo.WeatherInfoServiceImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class WeatherInfoModule {

    @Binds
    abstract fun bindWeatherInfoService(weatherInfoServiceImpl: WeatherInfoServiceImpl): WeatherInfoService

}