package au.com.bestnearme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.bestnearme.component.DaggerApplicationComponent
import au.com.bestnearme.services.CachePrefs

class LauncherViewModel : ViewModel() {

    val mIsIntroShown = MutableLiveData<Boolean>()

    init {
        DaggerApplicationComponent.create().inject(this)
    }

    fun checkCurrentState(isIntroShown : Boolean) {
        mIsIntroShown.value = isIntroShown
    }


}