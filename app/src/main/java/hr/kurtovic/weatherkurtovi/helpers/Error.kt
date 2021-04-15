package hr.kurtovic.weatherkurtovi.helpers

import androidx.annotation.StringRes
import hr.kurtovic.weatherkurtovi.R

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
