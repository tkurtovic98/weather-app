package hr.kurtovic.weatherkurtovi.helpers

import hr.kurtovic.weatherkurtovi.R

class IconLoader {

    companion object {
        fun loadBasicWeatherIconFromId(id: String) =
                when (id) {
                    "01d" -> R.mipmap.ic_01d
                    "01n" -> R.mipmap.ic_01n
                    "02d" -> R.mipmap.ic_02n
                    "02n" -> R.mipmap.ic_02n
                    "03d" -> R.mipmap.ic_03d
                    "03n" -> R.mipmap.ic_03n
                    "04d" -> R.mipmap.ic_04d
                    "04n" -> R.mipmap.ic_04n
                    "09d" -> R.mipmap.ic_09d
                    "09n" -> R.mipmap.ic_09n
                    "10d" -> R.mipmap.ic_10d
                    "10n" -> R.mipmap.ic_10n
                    "11n" -> R.mipmap.ic_11n
                    "13d" -> R.mipmap.ic_13d
                    "13n" -> R.mipmap.ic_13n
                    "50d" -> R.mipmap.ic_50d
                    "50n" -> R.mipmap.ic_50n
                    else -> R.mipmap.ic_unknown
                }
    }

}