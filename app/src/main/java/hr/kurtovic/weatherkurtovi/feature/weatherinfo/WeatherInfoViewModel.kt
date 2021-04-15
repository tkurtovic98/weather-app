package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import androidx.annotation.StringRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.kurtovic.weatherkurtovi.R
import hr.kurtovic.weatherkurtovi.helpers.Box
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class State(
    val cityToSearch: String = "",
    val weatherInfo: WeatherInfo? = null,
    val isWeatherInfoLoadingInProgress: Boolean = false,
    val errorMessageResId: Box<Int?> = Box()

)

sealed class Event {
    data class CitySearchTextChange(val citySearchText: String) : Event()
    data class WeatherInfoLoaded(val weatherInfo: WeatherInfo?) : Event()
    data class ErrorEvent(val error: Error) : Event()
    object GetWeatherInfo : Event()
}

sealed class Error {
    object GenericError : Error() {
        @StringRes
        fun errorMessageResId(): Int =
                R.string.error_generic_message
    }

    object InvalidInputError : Error() {
        @StringRes
        fun errorMessageResId(): Int =
                R.string.error_invalid_input_message

    }
}

fun reduce(state: State, event: Event): State =
        when (event) {

            is Event.CitySearchTextChange -> state.copy(cityToSearch = event.citySearchText)

            is Event.WeatherInfoLoaded -> state.copy(
                isWeatherInfoLoadingInProgress = false,
                weatherInfo = event.weatherInfo,
                cityToSearch = ""
            )

            is Event.ErrorEvent -> {
                val errorMessageResId = when (val error = event.error) {
                    is Error.GenericError -> error.errorMessageResId()
                    is Error.InvalidInputError -> error.errorMessageResId()
                }

                state.copy(
                    errorMessageResId = Box(errorMessageResId),
                    isWeatherInfoLoadingInProgress = false,
                    cityToSearch = ""
                )

            }

            Event.GetWeatherInfo -> state.copy(isWeatherInfoLoadingInProgress = true)
        }

class WeatherInfoViewModel @ViewModelInject constructor(
    private val weatherInfoService: WeatherInfoService
) :
    ViewModel() {

    private val internalState = MutableLiveData<State>().apply {
        value = State()
    }

    private val compositeDisposable = CompositeDisposable()

    val state: LiveData<State> = internalState

    fun onEvent(event: Event) {
        val currentState = internalState.value!!
        val newState = reduce(currentState, event)
        internalState.postValue(newState)

        if (newState.isWeatherInfoLoadingInProgress) {
            getWeatherInfoFor(newState.cityToSearch)
        }

    }

    private fun inputNotValid(cityName: String): Boolean = cityName.isEmpty()

    private fun cleanUpCityName(cityName: String) = cityName.capitalize()

    private fun getWeatherInfoFor(cityName: String) {

        if (inputNotValid(cityName)) {
            onEvent(Event.ErrorEvent(Error.InvalidInputError))
            return
        }

        val query = cleanUpCityName(cityName)

        compositeDisposable.add(
            weatherInfoService.getWeatherInfoFor(cityName = query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { success, error ->
                        success?.apply {
                            onEvent(Event.WeatherInfoLoaded(this))
                        }
                        error?.apply {
                            onEvent(Event.ErrorEvent(Error.GenericError))
                        }

                    }
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}