package hr.kurtovic.weatherkurtovi.helpers

import hr.kurtovic.weatherkurtovi.R

class IconLoader {

    companion object {
        fun loadBasicWeatherIconFromId(id: String): Int =
                when (id) {
                    "01d", "01n" -> R.drawable.ic_01n
                    "02d", "02n" -> R.drawable.ic_02n
                    "03d", "03n" -> R.drawable.ic_03n
                    "04d", "04n" -> R.drawable.ic_04n
                    "09d", "09n" -> R.drawable.ic_09n
                    "10d", "10n" -> R.drawable.ic_10n
                    "11d", "11n" -> R.drawable.ic_11n
                    "13d", "13n" -> R.drawable.ic_13d
                    "50d", "50n" -> R.drawable.ic_50d
                    else -> R.drawable.ic_unknown
                }
    }
}
