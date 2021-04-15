package hr.kurtovic.weatherkurtovi.models

import com.google.gson.annotations.SerializedName

data class WeatherInfo(

    @SerializedName("id")
    val id: Long,

    @SerializedName("weather")
    val weather: Array<BasicWeatherInfo>,

    @SerializedName("main")
    val main: MainWeatherInfo,

    @SerializedName("wind")
    val wind: WindInfo,

    @SerializedName("name")
    val name: String,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherInfo

        if (id != other.id) return false
        if (!weather.contentEquals(other.weather)) return false
        if (main != other.main) return false
        if (wind != other.wind) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + weather.contentHashCode()
        result = 31 * result + main.hashCode()
        result = 31 * result + wind.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "$name, \n ${weather[0]}, \n $main, \n $wind "
    }
}

data class BasicWeatherInfo(

    @SerializedName("id")
    val id: Long,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String

) {
    override fun toString(): String {
        return "Basic weather info: $main, $description"
    }
}

data class MainWeatherInfo(

    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("humidity")
    val humidity: Double

) {
    override fun toString(): String {
        return "Main weather info: $temperature, $pressure, $humidity"
    }
}

data class WindInfo(

    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val degree: Double

) {
    override fun toString(): String {
        return "Wind info: $speed, $degree"
    }
}
