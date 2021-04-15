package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.kurtovic.weatherkurtovi.helpers.Error
import hr.kurtovic.weatherkurtovi.helpers.LiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherInfoViewModel @ViewModelInject constructor(
    private val weatherInfoService: WeatherInfoService
) :
    ViewModel() {

    private val internalWeatherInfo = MutableLiveData<WeatherInfoUiModel>()

    private val compositeDisposable = CompositeDisposable()

    val weatherInfo: LiveData<WeatherInfoUiModel> = internalWeatherInfo

    val errorEvent = LiveEvent<Int>()

    fun getWeatherInfoFor(cityName: String) {

        if (inputNotValid(cityName)) {
            errorEvent.postValue(Error.InvalidInputError.errorMessageResId())
            return
        }

        val query = cleanUpCityName(cityName)

        compositeDisposable.add(
            weatherInfoService.getWeatherInfoFor(cityName = query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { success, error ->
                        success?.apply {
                            val weatherInfoUiModel = fromWeatherInfoToUiModel(this)
                            internalWeatherInfo.postValue(weatherInfoUiModel)
                        }
                        error?.apply {
                            errorEvent.postValue(Error.GenericError.errorMessageResId())
                        }
                    }
        )
    }

    private fun inputNotValid(cityName: String): Boolean = cityName.isEmpty()

    private fun cleanUpCityName(cityName: String) = cityName.capitalize()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}