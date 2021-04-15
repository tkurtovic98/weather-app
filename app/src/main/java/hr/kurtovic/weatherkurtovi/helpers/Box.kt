package hr.kurtovic.weatherkurtovi.helpers

class Box<T>(var data: T? = null) {

    inline fun take(f: (T) -> Unit): Unit {
        data?.apply(f)
        this.data = null
    }

    fun put(newData: T): Unit {
        data = newData
    }


}