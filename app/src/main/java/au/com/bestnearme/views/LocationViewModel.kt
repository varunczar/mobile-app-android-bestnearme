package au.com.bestnearme.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import au.com.bestnearme.services.LocationLiveData

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData
}