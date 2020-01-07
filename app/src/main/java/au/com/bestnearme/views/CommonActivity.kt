package au.com.bestnearme.views

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import au.com.bestnearme.services.CachePrefs
import au.com.bestnearme.views.utils.Constants.REQUEST_GPS
import au.com.bestnearme.views.utils.Constants.REQUEST_LOCATION
import au.com.bestnearme.views.utils.GpsUtils

abstract class CommonActivity : AppCompatActivity() {

    lateinit var mLocationViewModel: LocationViewModel
    private var isGPSEnabled = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLocationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        initiateLocationPermissionCheck()

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GPS) {
                isGPSEnabled = true
                invokeLocationAction()
            }
        }
    }

    fun initiateLocationPermissionCheck() {
        GpsUtils(this).turnGPSOn(object : GpsUtils.OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                this@CommonActivity.isGPSEnabled = isGPSEnable
            }
        })
    }

    abstract fun locationEnabled()

    fun invokeLocationAction() {
        when {

            isPermissionsGranted() -> {
                locationEnabled()
                startLocationUpdate()
            }

            else -> ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_LOCATION
            )
        }
    }

    private fun startLocationUpdate() {
        mLocationViewModel.getLocationData().observe(this, Observer {
            CachePrefs.saveLastKnownLocation(it, this@CommonActivity)
        })
    }

    private fun isPermissionsGranted() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_LOCATION -> {
                invokeLocationAction()
            }
        }
    }

}