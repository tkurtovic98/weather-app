package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hr.kurtovic.weatherkurtovi.R
import hr.kurtovic.weatherkurtovi.helpers.hideKeyboard
import hr.kurtovic.weatherkurtovi.helpers.showKeyboard
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import kotlinx.android.synthetic.main.city_search_tab.*
import kotlinx.android.synthetic.main.fragment_weather_info.*

@AndroidEntryPoint
class WeatherInfoFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherInfoFragment()
    }

    private val weatherInfoViewModel: WeatherInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_weather_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestButton.setOnClickListener { getWeatherInfoForCity() }

        citySearchInput.requestFocus()
        showKeyboard()

        citySearchInput.setOnKeyListener { _, _, keyEvent ->
            handleKeyEvent(keyEvent)
        }

        citySearchInput.doAfterTextChanged {
            weatherInfoViewModel.onEvent(
                Event.CitySearchTextChange(
                    it.toString()
                )
            )
        }

        weatherInfoViewModel.state.observe(viewLifecycleOwner, { render(it) })
    }

    private fun handleKeyEvent(keyEvent: KeyEvent): Boolean {
        if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
            getWeatherInfoForCity()
            return true
        }
        return false
    }

    private fun getWeatherInfoForCity() {
        weatherInfoViewModel.onEvent(Event.GetWeatherInfo)
        clearInputField()
    }

    private fun clearInputField() {
        citySearchInput.text.clear()
    }

    private fun render(state: State) {

        if (state.isWeatherInfoGettingInProgress) {
            hideKeyboard()
            return
        }

        state.weatherInfo?.let { weatherInfo ->
            renderWeatherInfo(weatherInfo)
        }

        state.errorMessageResId?.let {
            Snackbar.make(requireView(), it.toString(), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun renderWeatherInfo(weatherInfo: WeatherInfo) {
        cityNameText.text = weatherInfo.name
        basicWeatherIcon.text = weatherInfo.weather[0].main
        temperatureText.text = weatherInfo.main.temperature.toString()
        humidityText.text = weatherInfo.main.humidity.toString()
        pressureText.text = weatherInfo.main.pressure.toString()
        windSpeedText.text = weatherInfo.wind.speed.toString()
        windDegreeText.text = weatherInfo.wind.degree.toString()
    }
}
