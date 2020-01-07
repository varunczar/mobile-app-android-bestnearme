package au.com.bestnearme

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import au.com.bestnearme.services.CachePrefs
import au.com.bestnearme.views.categories.CategoriesActivity
import au.com.bestnearme.views.intro.IntroActivity
import au.com.bestnearme.views.intro.IntroViewModel
import au.com.bestnearme.views.places.PlacesViewModel
import au.com.bestnearme.views.utils.Constants.REQUEST_INTRO

class LauncherActivity : AppCompatActivity() {

    lateinit var mLauncherViewModel : LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLauncherViewModel = ViewModelProviders.of(this).get(LauncherViewModel::class.java)

        observeViewModels()
        mLauncherViewModel.checkCurrentState(CachePrefs.isIntroShown(this))
    }

    fun observeViewModels(){
        mLauncherViewModel.mIsIntroShown.observe(this, Observer { isIntroShown ->
            isIntroShown?.let {
                if(it) {
                    startActivity(Intent(this@LauncherActivity, CategoriesActivity::class.java))
                    finish()
                }
                else {
                    startActivity(Intent(this@LauncherActivity, IntroActivity::class.java))
                    finish()
                }
            }
        })
    }

}
