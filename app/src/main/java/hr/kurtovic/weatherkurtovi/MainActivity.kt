package hr.kurtovic.weatherkurtovi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import hr.kurtovic.weatherkurtovi.feature.weatherinfo.WeatherInfoFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showMainScreen()
        }
    }

    private fun showMainScreen() {
        replaceFragment(WeatherInfoFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val tag = fragment::class.simpleName
        val currentFragment = supportFragmentManager.findFragmentByTag(tag)
        if (currentFragment?.isVisible == true) {
            return
        }

        supportFragmentManager.commit {
            if (addToBackStack) {
                addToBackStack(tag)
            }
            replace(R.id.fragmentContainer, fragment, tag)
        }
    }
}
