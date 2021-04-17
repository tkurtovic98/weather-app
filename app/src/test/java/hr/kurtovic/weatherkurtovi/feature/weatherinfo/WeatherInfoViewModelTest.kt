package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class WeatherInfoViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherInfoViewModel: WeatherInfoViewModel

    private lateinit var errorObserver: Observer<Int>

    @Before
    fun initialize() {
        weatherInfoViewModel = WeatherInfoViewModel(weatherInfoService = FakeWeatherInfoService())
        errorObserver = Observer<Int> {}
        weatherInfoViewModel.errorEvent.observeForever(errorObserver)
    }

    @After
    fun clean() {
        weatherInfoViewModel.errorEvent.removeObserver(errorObserver)
    }

    @Test
    fun emptyInput_setsErrorEvent() {
        weatherInfoViewModel.getWeatherInfoFor("")

        val value = weatherInfoViewModel.errorEvent.value

        assertThat(value).isNotNull()
    }

    @Test
    fun numberInInput_setsErrorEvent() {
        weatherInfoViewModel.getWeatherInfoFor("1")

        val value = weatherInfoViewModel.errorEvent.value

        assertThat(value).isNotNull()

    }

}