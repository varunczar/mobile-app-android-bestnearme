package au.com.bestnearme.services

import android.content.Context
import android.util.Log
import au.com.bestnearme.data.location.LocationModel
import au.com.bestnearme.views.utils.Constants.FILE_NAME_CACHE
import au.com.bestnearme.views.utils.Constants.KEY_IS_INTRO_SHOWN
import au.com.bestnearme.views.utils.Constants.KEY_LAST_KNOWN_LAT
import au.com.bestnearme.views.utils.Constants.KEY_LAST_KNOWN_LONG

object CachePrefs : BasePrefs(FILE_NAME_CACHE)
{

    fun saveLastKnownLocation(location: LocationModel, context: Context) {
        getWritePrefs(context).putFloat(KEY_LAST_KNOWN_LAT, location.latitude.toFloat()).commit()
        getWritePrefs(context).putFloat(KEY_LAST_KNOWN_LONG, location.longitude.toFloat()).commit()
    }

    fun getLastKnownLatLong(context: Context) : LocationModel? {
        val lat = getReadPrefs(context).getFloat(KEY_LAST_KNOWN_LAT,0.0F)
        val long = getReadPrefs(context).getFloat(KEY_LAST_KNOWN_LONG,0.0F)
        if(lat != 0.0F && long != 0.0F) {
            val location = LocationModel(latitude = lat.toDouble(),
                longitude = long.toDouble())

            return location
        }
        return null
    }

    fun setIntroShown(value : Boolean, context: Context) {
        getWritePrefs(context).putBoolean(KEY_IS_INTRO_SHOWN, value).commit()
    }

    fun isIntroShown(context: Context) : Boolean {
        return getReadPrefs(context).getBoolean(KEY_IS_INTRO_SHOWN, false)
    }
}