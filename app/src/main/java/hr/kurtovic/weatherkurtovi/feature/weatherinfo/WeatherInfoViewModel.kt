package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import androidx.annotation.StringRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.kurtovic.weatherkurtovi.R
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class State(
    val cityToSearch: String = "",
    val weatherInfo: WeatherInfo? = null,
    val isWeatherInfoGettingInProgress: Boolean = false,
    val errorMessageResId: Int? = null

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
}

fun reduce(state: State, event: Event): State =
        when (event) {

            is Event.CitySearchTextChange -> state.copy(cityToSearch = event.citySearchText)

            is Event.WeatherInfoLoaded -> state.copy(
                isWeatherInfoGettingInProgress = false,
                weatherInfo = event.weatherInfo,
                cityToSearch = ""
            )

            is Event.ErrorEvent -> {
                val errorMessageResId = when (val error = event.error) {
                    is Error.GenericError -> error.errorMessageResId()
                }

                state.copy(
                    errorMessageResId = errorMessageResId,
                    isWeatherInfoGettingInProgress = false,
                    cityToSearch = ""
                )

            }

            Event.GetWeatherInfo -> state.copy(isWeatherInfoGettingInProgress = true)
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

        if (newState.isWeatherInfoGettingInProgress) {
            getWeatherInfoFor(newState.cityToSearch)
        }

        if (newState.errorMessageResId != null) {
            internalState.postValue(newState.copy(errorMessageResId = null))
        }
    }

    private fun getWeatherInfoFor(cityName: String) {

        compositeDisposable.add(
            weatherInfoService.getWeatherInfoFor(cityName = cityName)
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