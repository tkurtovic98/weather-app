package hr.kurtovic.weatherkurtovi.helpers

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Activity.showKeyboard() {
    currentFocus?.apply {
        (this@showKeyboard.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(this, 0)
    }
}

fun Activity.hideKeyboard() {
    currentFocus?.apply {
        (this@hideKeyboard.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(this.windowToken, 0)
    }
}

fun Fragment.showKeyboard() = activity?.showKeyboard()
fun Fragment.hideKeyboard() = activity?.hideKeyboard()