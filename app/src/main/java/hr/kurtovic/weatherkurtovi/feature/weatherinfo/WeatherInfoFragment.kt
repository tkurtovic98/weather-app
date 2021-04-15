package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hr.kurtovic.weatherkurtovi.R
import hr.kurtovic.weatherkurtovi.helpers.IconLoader
import hr.kurtovic.weatherkurtovi.helpers.hideSoftKeyboard
import hr.kurtovic.weatherkurtovi.helpers.showSoftKeyboard
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

        setupUi()
        setupWeatherInfoObserver()
        setupErrorEventObserver()
    }

    private fun setupUi() {
        setupEmptyScreen()
        setupRequestButton()
        setupCitySearchInput()
    }

    private fun setupEmptyScreen() {
        emptyScreen.isVisible = true
        weatherInfoContainer.isVisible = false
    }

    private fun setupRequestButton() {
        requestButton.setOnClickListener { getWeatherInfoForCity() }
    }

    private fun setupCitySearchInput() {

        citySearchInput.postDelayed(20) {
            citySearchInput.isFocusable = true
            citySearchInput.requestFocus()
            citySearchInput.showSoftKeyboard()
        }

        citySearchInput.setOnKeyListener { _, _, keyEvent ->
            handleKeyEvent(keyEvent)
        }
    }

    private fun handleKeyEvent(keyEvent: KeyEvent): Boolean {
        if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
            getWeatherInfoForCity()
            return true
        }
        return false
    }

    private fun getWeatherInfoForCity() {
        setupLoadingIndicatorHideOthers()
        weatherInfoViewModel.getWeatherInfoFor(citySearchInput.text.toString())
        clearInputField()
        citySearchInput.clearFocus()
        citySearchInput.hideSoftKeyboard()
    }

    private fun setupLoadingIndicatorHideOthers() {
        emptyScreen.isVisible = false
        weatherInfoContainer.isVisible = false
        loadingIndicator.isVisible = true
    }

    private fun clearInputField() {
        citySearchInput.text.clear()
    }

    private fun setupWeatherInfoObserver() {
        weatherInfoViewModel.weatherInfo.observe(viewLifecycleOwner, { renderWeatherInfo(it) })
    }

    private fun renderWeatherInfo(weatherInfo: WeatherInfoUiModel) {

        weatherInfo.let {
            populateFields(it)
            emptyScreen.isVisible = false
            loadingIndicator.isVisible = false
            weatherInfoContainer.isVisible = true
        }
    }

    private fun populateFields(weatherInfo: WeatherInfoUiModel) {

        cityNameText.text = weatherInfo.name
        temperatureText.text = getString(R.string.units_metric_temperature, weatherInfo.temperature)
        humidityText.text = getString(R.string.units_metric_humidity, weatherInfo.humidity)
        pressureText.text = getString(R.string.units_metric_pressure, weatherInfo.pressure)
        windSpeedText.text = getString(
            R.string.units_metric_wind_speed,
            weatherInfo.windSpeed,
            weatherInfo.windDirection
        )
        basicWeatherDescriptionText.text = weatherInfo.basicDescription

        populateBasicWeatherIconFrom(weatherInfo.iconId)
    }

    private fun populateBasicWeatherIconFrom(iconId: String) {
        val basicWeatherIconResId = IconLoader.loadBasicWeatherIconFromId(iconId)
        val iconDrawable = ResourcesCompat.getDrawable(
            resources,
            basicWeatherIconResId,
            activity?.theme
        )
        basicWeatherIcon.setImageDrawable(iconDrawable)
    }

    private fun setupErrorEventObserver() {
        weatherInfoViewModel.errorEvent.observe(viewLifecycleOwner, { renderError(it) })
    }

    private fun renderError(errorMessageId: Int) {
        hideLoadingIndicatorShowOther()
        Snackbar.make(requireView(), getString(errorMessageId), Snackbar.LENGTH_SHORT).show()
    }

    private fun hideLoadingIndicatorShowOther() {
        loadingIndicator.isVisible = false

        if (cityNameText.text.isNotEmpty()) {
            weatherInfoContainer.isVisible = true
        } else {
            emptyScreen.isVisible = true
        }
    }
}
