package au.com.bestnearme.services

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import au.com.bestnearme.MyApplication

abstract class BasePrefs(val mFileName: String) {


    fun getPrefsInstance(context: Context) : SharedPreferences {
        return context.getSharedPreferences(mFileName,0)
    }

    fun getReadPrefs(context: Context) : SharedPreferences {
        return getPrefsInstance(context)
    }

    fun getWritePrefs(context: Context) : SharedPreferences.Editor {
        return getPrefsInstance(context).edit()
    }

}