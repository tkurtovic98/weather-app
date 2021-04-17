package hr.kurtovic.weatherkurtovi.feature.weatherinfo

import com.google.common.truth.Truth.assertThat
import hr.kurtovic.weatherkurtovi.models.BasicWeatherInfo
import hr.kurtovic.weatherkurtovi.models.MainWeatherInfo
import hr.kurtovic.weatherkurtovi.models.WeatherInfo
import hr.kurtovic.weatherkurtovi.models.WindInfo
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class WeatherInfoUiModelTest {

    lateinit var givenWeatherInfo: WeatherInfo

    @Before
    fun beforeAll() {
        val givenWeatherInfoId: Long = 123
        val givenMainWeatherInfo = MainWeatherInfo(
            temperature = 10.23,
            humidity = 56.04,
            pressure = 1033.23
        )
        val givenWeatherArray = arrayOf(
            BasicWeatherInfo(
                id = 1,
                main = "Cloudy",
                description = "Mostly clouds",
                icon = "01d"
            )
        )

        val givenWindInfo = WindInfo(speed = 3.5, degree = 55.5)

        val givenName = "London"

        givenWeatherInfo = WeatherInfo(
            id = givenWeatherInfoId,
            main = givenMainWeatherInfo,
            weather = givenWeatherArray,
            wind = givenWindInfo,
            name = givenName
        )
    }


    @Test
    fun fromWeatherInfoToUiModel_randomData_returnsCorrectUiModel() {

        val weatherInfo = givenWeatherInfo
        val givenWeatherArray = givenWeatherInfo.weather
        val givenName = givenWeatherInfo.name

        val weatherInfoUiModel = fromWeatherInfoToUiModel(weatherInfo)

        assertThat(weatherInfoUiModel.iconId).isEqualTo(givenWeatherArray[0].icon)
        assertThat(weatherInfoUiModel.windDirection).isEqualTo("NE")
        assertThat(weatherInfoUiModel.windSpeed).isEqualTo("4")
        assertThat(weatherInfoUiModel.humidity).isEqualTo("56")
        assertThat(weatherInfoUiModel.temperature).isEqualTo("10")
        assertThat(weatherInfoUiModel.name).isEqualTo(givenName)
        assertThat(weatherInfoUiModel.basicDescription).isEqualTo(givenWeatherArray[0].main)
    }

    @Test
    fun fromWeatherInfoToUiModel_nameIsEmpty_throwsException() {

        val weatherInfo = givenWeatherInfo.copy(name = "")

        try {
            fromWeatherInfoToUiModel(weatherInfo)
            fail("Method should throw exception on empty name")
        } catch (e: Exception) {
            assertThat(e).hasMessageThat()
                    .contains(WeatherInfoValidationStatus.EmptyCityError.message())
        }

    }

    @Test
    fun fromWeatherInfoToUiModel_weatherArrayIsEmpty_throwsException() {

        val weatherInfo = givenWeatherInfo.copy(weather = emptyArray())

        try {
            fromWeatherInfoToUiModel(weatherInfo)
            fail("Method should throw exception on empty weather array")
        } catch (e: Exception) {
            assertThat(e).hasMessageThat()
                    .contains(WeatherInfoValidationStatus.EmptyWeatherArrayError.message())
        }

    }


}